package com.hoaoen.framework.mybatis.core;

import com.hoaoen.framework.mybatis.generator.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author horaoen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityMetadata {

    private Class<?> entityClass;

    private String tableName;

    private FieldMetadata idMetadata;

    private IdGenerator idGenerator;
    
    private FieldMetadata logicDeleteMetadata;
    
    private FieldMetadata versionMetadata;

    private List<FieldMetadata> fields = new ArrayList<>();

    public EntityMetadata(Class<?> entityClass) {
        this.entityClass = entityClass;
    }
}