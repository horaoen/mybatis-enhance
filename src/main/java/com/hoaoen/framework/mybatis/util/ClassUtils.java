package com.hoaoen.framework.mybatis.util;

import java.lang.reflect.ParameterizedType;

/**
 * @author horaoen
 */
public class ClassUtils {
    public static Class<?> getEntityClass(Class<?> mapperClass) {
        ParameterizedType type = (ParameterizedType) mapperClass.getGenericInterfaces()[0];
        return (Class<?>) type.getActualTypeArguments()[0];
    }
}
