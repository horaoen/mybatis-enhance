package com.hoaoen.framework.mybatis.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @author horaoen
 */
public interface BaseMapper<T> {
    @InsertProvider(type = Insert.class, method = "invoke")
    int insert(T entity);

    @UpdateProvider(type = Update.class, method = "invoke")
    int update(T entity);

    @SelectProvider(type = FindAll.class, method = "invoke")
    List<T> findAll();
    
    @DeleteProvider(type = Delete.class, method = "invoke")
    int delete(T entity);

    @SelectProvider(type = FindByExample.class, method = "invoke")
    List<T> findByExample(T example);
    
}
