package com.hoaoen.framework.mybatis.mapper;

import com.hoaoen.framework.mybatis.core.EntityMetadata;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author horaoen
 */
public class FindAll extends AbstractProvider{
    @Override
    protected String buildSql(EntityMetadata entityMetadata, Object params) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(entityMetadata.getTableName());
        if (entityMetadata.getLogicDeleteMetadata() != null) {
            sql.WHERE(entityMetadata.getLogicDeleteMetadata().getColumnName() + " = 0");
        }
        return sql.toString();
    }
}
