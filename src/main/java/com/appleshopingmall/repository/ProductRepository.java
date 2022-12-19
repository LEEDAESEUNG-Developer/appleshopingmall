package com.appleshopingmall.repository;

import com.appleshopingmall.controller.web.Criteria;
import com.appleshopingmall.entity.ProductEntity;
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

    // product 테이블에 중복제거
    List<String> findByDeduplicationProduct(@Param("column") String column);

    // 페이징 처리 코드
    List<ProductEntity> getListWithPaging(Criteria cri);

    // 페이징 type이 있는 경우 (Mac, iPhone, Watch) 등
    List<ProductEntity> getListWithPagingType(Criteria cri);

    // 페이징 type, ap, color
    List<ProductEntity> findListWithPagingTypeAndApAndColor(Criteria cri);

    // 페이징 type, ap
    List<ProductEntity> findListWithPagingTypeAndAp(Criteria cri);

    // product 테이블 row 수
    int count();

    // product 테이블 카테고리 row 수
    int countType(Criteria cri);

    // product 테이블 Ap row 수
    int countAp(Criteria cri);

    // product 테이블 color row 수
    int countColor(Criteria cri);

    // product 테이블 카테고리, ap, 색상 row 수
    int countAndTypeAnyApAndColor(Criteria cri);

    // product 테이블 카테고리, ap row 수
    int countAndTypeAndAp(Criteria cri);

    // product 테이블 Ap, 색상 row 수
    int countAndApAndColor(Criteria cri);

    // product ap
    List<ProductEntity> findListWithPagingAp(Criteria cri);

    // product color
    List<ProductEntity> findListWithPagingColor(Criteria cri);

    // product ap and color
    List<ProductEntity> findListWithPagingApAndColor(Criteria cri);

    // 제품 색상 가져오기
    List<String> getProductColorByProductName(@Param("productName") String productName);

    /**
     * 제품 번호를 조회해서 stock(수량)를 가져옴
     * @param productId Long
     * @return 상품 수량
     */
    int findByProductIdStockCount(@Param("productId") Long productId);


    /**
     * 제품 아이디에 재고 수정
     * @param productId 제품 아이디
     * @param stock 제품 재고
     * @return 성공시 1, 실패시 0
     */
    int updateStock(@Param("productId") Long productId, @Param("stock") long stock);
}
