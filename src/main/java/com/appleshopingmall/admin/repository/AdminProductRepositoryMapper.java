package com.appleshopingmall.admin.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminProductRepositoryMapper extends AdminProductRepository{

    // 카테고리 번호로 조회해서 카테고리명 리턴
    @ResultType(java.lang.String.class)
    @Select("select catagory_subject from product_catagory where catagory_id = #{categoryId}")
    String findByCategoryId(int categoryId);
}
