package com.hoaoen.framework.mybatis.core;


import com.hoaoen.framework.mybatis.annotation.*;
import com.hoaoen.framework.mybatis.util.ClassUtils;
import com.hoaoen.framework.mybatis.util.StringConvertUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author horaoen
 */

public class EntityMetadataManager {

    public static ConcurrentMap<Class<?>, EntityMetadata> entityMetadataMap = new ConcurrentHashMap<>();

    private static void registerMetadata(EntityMetadata metadata) {
        entityMetadataMap.put(metadata.getEntityClass(), metadata);
    }

    public static EntityMetadata getMetadata(Class<?> entityClass) {
        EntityMetadata entityMetadata = entityMetadataMap.get(entityClass);

        if(entityMetadata == null) {
            EntityMetadata finalEntityMetadata = new EntityMetadata(entityClass);

            finalEntityMetadata.setTableName(resolveTableName(entityClass));
            ReflectionUtils.doWithFields(entityClass, field -> {
                // 列名的处理（约定大于配置）
                Column columnAnno = field.getAnnotation(Column.class);
                String column = columnAnno != null && StringUtils.hasText(columnAnno.value())
                        ? columnAnno.value()
                        : StringConvertUtils.camelCaseToUnderscore(field.getName());

                FieldMetadata fieldMetadata = new FieldMetadata(field.getName(), column);
                
                // 解析自定义注解
                if (field.isAnnotationPresent(Id.class)) {
                    fieldMetadata.setPrimary(true);
                    finalEntityMetadata.setIdMetadata(new FieldMetadata(field.getName(), column));
                    finalEntityMetadata.setIdGenerator(BeanUtils.instantiateClass(field.getAnnotation(Id.class).generateStrategy()));
                }
                if (field.isAnnotationPresent(LogicDelete.class)) {
                    finalEntityMetadata.setLogicDeleteMetadata(fieldMetadata);
                }
                if(field.isAnnotationPresent(Version.class)) {
                    finalEntityMetadata.setVersionMetadata(fieldMetadata);
                }
                if(field.isAnnotationPresent(Equals.class)) {
                    fieldMetadata.setQuerySql(column + " = #{" + field.getName() + "}");
                } else if(field.isAnnotationPresent(Like.class)) {
                    Like like = field.getAnnotation(Like.class);
                    fieldMetadata.setQuerySql(column + " like concat(" + like.type().getPattern().replace("#", "#{" + field.getName() + "}") + ")");
                }
                
                finalEntityMetadata.getFields().add(fieldMetadata);
                if (columnAnno != null) {
                    fieldMetadata.setUpdateIfNull(columnAnno.updateIfNull());
                } 
                
            }, field -> !field.isAnnotationPresent(Transient.class) && !Modifier.isStatic(field.getModifiers()));

            // 实体类元信息存放至统一容器
            entityMetadata = finalEntityMetadata;
            EntityMetadataManager.registerMetadata(entityMetadata);
        }

        return entityMetadata;
    }

    public static EntityMetadata getMetadataByMapper(Class<?> mapperClass) {
        return getMetadata(ClassUtils.getEntityClass(mapperClass));
    }

    private static String resolveTableName(Class<?> entityClass) {
        if (entityClass.isAnnotationPresent(Table.class)) {
            return entityClass.getAnnotation(Table.class).value();
        }
        String className = entityClass.getName();
        return StringConvertUtils.camelCaseToUnderscore(Introspector.decapitalize(className));
    }

}