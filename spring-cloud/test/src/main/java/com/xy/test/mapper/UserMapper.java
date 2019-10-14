package com.xy.test.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @author xy
 */
@Mapper
public interface UserMapper {

    /**
     * 新增数据
     *
     * @param name 名字
     */
    @Insert("INSERT INTO user(name) VALUES(#{name})")
    void insert(String name);
}
