package com.appleshopingmall.shop.product;

import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ProductRepository {

    // 제품 테이블 컬럼 전체 조회
    List<ProductEntity> findAll();

    // 제품 조회
    List<ProductEntity> findByProductView();

    // 재품번호로 제품 조회 가져오기
    List<ProductEntity> findByProductName(@Param("productName") String productName);

    // 제품이름, 컬러 조회
    ProductEntity findByProductNameAndColor(@Param("productName") String productName, @Param("color") String color);

    // 제품이름으로 색상을 조회
    List<ProductEntity> findByProductColor(@Param("productName") String productName);

    // 전채 페이지 카운팅
//    Integer pageCount(PagingEntity page);

//    List<ProductEntity> findByPageProductView(@Param("page") PagingEntity page);
}
