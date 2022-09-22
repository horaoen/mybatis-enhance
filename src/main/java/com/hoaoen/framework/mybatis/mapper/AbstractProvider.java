package com.hoaoen.framework.mybatis.mapper;

import com.hoaoen.framework.mybatis.core.EntityMetadata;
import com.hoaoen.framework.mybatis.core.EntityMetadataManager;
import org.apache.ibatis.builder.annotation.ProviderContext;

/**
 * @author horaoen
 */
public abstract class AbstractProvider {

    public String invoke(Object params, ProviderContext context) {
        EntityMetadata entityMetadata = EntityMetadataManager.getMetadataByMapper(context.getMapperType());
        return buildSql(entityMetadata, params);
    }

    protected abstract String buildSql(EntityMetadata entityMetadata, Object params);
}