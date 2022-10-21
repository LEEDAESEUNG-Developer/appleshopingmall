package com.appleshopingmall.shop.product;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {

    // 전체 제품 보기
    List<ProductEntity> findAll();

    // 제품 조회
    List<ProductEntity> findByProductView();

    // 재품번호로 제품 조회 가져오기
    List<ProductEntity> findByProductName(@Param("productName") String productName);

    // 제품이름, 컬러 조회
    ProductEntity findByProductNameAndColor(@Param("productName") String productName, @Param("color") String color);

    // 제품이름으로 색상을 조회
    List<ProductEntity> findByProductColor(@Param("productName") String productName);

    // 제품번호로 제품 가져오기
    ProductEntity findByProductNumber(@Param("productNumber") int productNumber);

    // 신제품 가져오기
    List<ProductEntity> findNewProduct();


}
