package com.hoaoen.framework.mybatis.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author horaoen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldMetadata {

    private String fieldName;

    private String columnName;

    private boolean primary = false;

    private boolean updateIfNull = false;

    private String querySql;

    public FieldMetadata(String fieldName, String columnName) {
        this.fieldName = fieldName;
        this.columnName = columnName;
    }
}