package com.hoaoen.framework.mybatis.annotation;

import com.hoaoen.framework.mybatis.generator.IdGenerator;
import com.hoaoen.framework.mybatis.generator.UuidGenerator;

import java.lang.annotation.*;

/**
 * @author horaoen
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Id {
    Class<? extends IdGenerator> generateStrategy() default UuidGenerator.class;
}