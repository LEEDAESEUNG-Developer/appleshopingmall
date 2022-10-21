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

    // 제품번호로 제품 가져오기
    ProductEntity findByProductNumber(@Param("productNumber") int productNumber);

    /**
     * 가장 최근에 나온 맥
     * @return 최근에 나온 맥 제품
     */
    ProductEntity findNewMac();

    /**
     * 가장 최근에 나온 아이폰
     * @return 최근에 나온 아이폰 제품
     */
    ProductEntity findNewIphone();
    /**
     * 가장 최근에 나온 아이패드
     * @return 최근에 나온 아이패드 제품
     */
    ProductEntity findNewIpad();

    // 전채 페이지 카운팅
//    Integer pageCount(PagingEntity page);

//    List<ProductEntity> findByPageProductView(@Param("page") PagingEntity page);
}
