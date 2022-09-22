package com.hoaoen.framework.mybatis.annotation;

import java.lang.annotation.*;

/**
 * @author horaoen
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Like {

    LikeType type() default LikeType.FULL;


    enum LikeType {
        FULL("'%', #, '%'"), LEFT("'%', #"), RIGHT("#, '%'");

        private String pattern;

        LikeType(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }
}