package com.hoaoen.framework.mybatis.generator;

/**
 * @author horaoen
 */
public interface IdGenerator<T> {

    T next();
}