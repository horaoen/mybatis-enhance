package com.hoaoen.framework.mybatis.generator;

import java.util.UUID;

/**
 * @author horaoen
 */
public class UuidGenerator implements IdGenerator<String> {

    @Override
    public String next() {
        // 这里我们就不要短横线了
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}