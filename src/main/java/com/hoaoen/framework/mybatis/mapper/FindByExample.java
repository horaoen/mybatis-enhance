package com.hoaoen.framework.mybatis.mapper;

import com.hoaoen.framework.mybatis.core.EntityMetadata;
import com.hoaoen.framework.mybatis.core.FieldMetadata;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * @author horaoen
 */
public class FindByExample extends AbstractProvider{
    @Override
    protected String buildSql(EntityMetadata entityMetadata, Object params) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(entityMetadata.getTableName());
        MetaObject metaObject = SystemMetaObject.forObject(params);
        if (entityMetadata.getLogicDeleteMetadata() != null) {
            FieldMetadata loginDeleteMetadata = entityMetadata.getLogicDeleteMetadata();
            sql.WHERE(loginDeleteMetadata.getColumnName() + " = 0");
        }
        entityMetadata.getFields().stream().filter(fm -> fm.getQuerySql() != null)
                .filter(fm -> metaObject.getValue(fm.getFieldName()) != null)
                .forEach(fm -> {
                    sql.WHERE(fm.getQuerySql());
                });
        return sql.toString();
    }
}
