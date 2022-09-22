package com.hoaoen.framework.mybatis.annotation;

import java.lang.annotation.*;

/**
 * @author horaoen
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

    String value();
    boolean updateIfNull() default false;
}