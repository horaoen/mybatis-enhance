package com.hoaoen.framework.mybatis.annotation;

import java.lang.annotation.*;

/**
 * @author horaoen
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {

    String value();
}