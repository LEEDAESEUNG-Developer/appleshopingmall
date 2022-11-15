package com.appleshopingmall.admin.repository;

import com.appleshopingmall.admin.entity.AdminProductEntity;
import com.appleshopingmall.admin.repository.dto.ProductAddDTO;

import java.util.List;

public interface AdminProductRepository {

    // 모든 제품 조회(복수)
    List<AdminProductEntity> findProducts();

    // 제품 조회 (단수)
    AdminProductEntity findProduct(Long productId);

    // 제품 추가
    int addProduct(ProductAddDTO product);

    // 제품 수정
    Integer updateProduct(AdminProductEntity product);

    // 제품 삭제
    Integer deleteProduct(Long productId);

    // 카테고리 번호로 조회해서 카테고리명 리턴
    String findByCategoryId(int categoryId);

}
