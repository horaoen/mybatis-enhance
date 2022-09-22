package com.hoaoen.framework.mybatis.mapper;

import com.hoaoen.framework.mybatis.core.EntityMetadata;
import com.hoaoen.framework.mybatis.core.FieldMetadata;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.List;

/**
 * @author horaoen
 */
public class Insert extends AbstractProvider{

    @Override
    protected String buildSql(EntityMetadata entityMetadata, Object params) {
        String tableName = entityMetadata.getTableName();
        List<FieldMetadata> fieldMetadatas = entityMetadata.getFields();
        String[] columns = fieldMetadatas.stream()
                .filter(fm -> !fm.getColumnName().equals(entityMetadata.getLogicDeleteMetadata().getColumnName()))
                .map(FieldMetadata::getColumnName).toArray(String[]::new);
        
        String[] fields = fieldMetadatas.stream()
                .filter(fm -> !fm.getColumnName().equals(entityMetadata.getLogicDeleteMetadata().getColumnName()))
                .map(fm -> "#{" + fm.getFieldName() + "}").toArray(String[]::new);

        SystemMetaObject.forObject(params).setValue(entityMetadata.getIdMetadata().getFieldName(), entityMetadata.getIdGenerator().next());

        SQL sql = new SQL();
        sql.INSERT_INTO(tableName).INTO_COLUMNS(columns).INTO_VALUES(fields);
        return sql.toString();
    }
}
