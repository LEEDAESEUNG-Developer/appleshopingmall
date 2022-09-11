package com.appleshopingmall.mybatisTest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;

@Mapper
public interface MybatisTest {

    @Select("select now()")
    Timestamp time();
}
