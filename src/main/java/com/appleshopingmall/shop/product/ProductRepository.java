package com.appleshopingmall.shop.product;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductRepository {

    // 전체 제품 보기
    List<ProductEntity> findAll();

    // 재품번호로 제품 조회 가져오기
    List<ProductEntity> findByProductName(@Param("productName") String productName);

    // 제품이름, 컬러 조회
    ProductEntity findByProductNameAndColor(@Param("productName") String productName, @Param("color") String color);
}
