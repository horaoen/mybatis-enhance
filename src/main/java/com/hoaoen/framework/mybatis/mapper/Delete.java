package com.hoaoen.framework.mybatis.mapper;

import com.hoaoen.framework.mybatis.core.EntityMetadata;
import com.hoaoen.framework.mybatis.core.FieldMetadata;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author horaoen
 */
public class Delete extends AbstractProvider {

    @Override
    protected String buildSql(EntityMetadata entityMetadata, Object params) {
        SQL sql = new SQL();
        FieldMetadata logicDeleteMetadata = entityMetadata.getLogicDeleteMetadata();
        FieldMetadata idMetadata = entityMetadata.getIdMetadata();
        // 针对有逻辑删除跟没有逻辑删除的情况做适配
        if (logicDeleteMetadata != null) {
            sql.UPDATE(entityMetadata.getTableName());
            sql.SET(logicDeleteMetadata.getColumnName() + " = 1");
            sql.WHERE(idMetadata.getColumnName() + " = #{" + idMetadata.getFieldName() + "}");
        } else {
            sql.DELETE_FROM(entityMetadata.getTableName());
            sql.WHERE(idMetadata.getColumnName() + " = #{" + idMetadata.getFieldName() + "}");
        }
        return sql.toString();
    }
}