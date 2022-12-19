package com.appleshopingmall.service;

import com.appleshopingmall.controller.web.Criteria;
import com.appleshopingmall.entity.ProductEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {

    // 전체 제품 보기
    List<ProductEntity> findAll();

    /**
     *
     * @param column 컬럼명
     * @return product 테이블에 중복제거
     */
    List<String> findByDeduplicationProduct(@Param("column") String column);

    // 제품 조회
    List<ProductEntity> findByProductView();

    /* 페이징 코드 */
    List<ProductEntity> findByProductPaging(Criteria cri);

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

    // product 테이블 row 수
    int count(Criteria cri);

    // 제품 색상 가져오기
    List<String> getProductColorByProductName(String productName);


    // 제품 번호를 조회해서 stock(수량)를 가져옴
    int findByProductIdStockCount(@Param("productId") Long productId);

}
