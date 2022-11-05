package com.appleshopingmall.admin.repository;

import com.appleshopingmall.shop.product.ProductEntity;

import java.util.List;

public interface AdminProductRepository {

    // 모든 제품 조회(복수)
    List<ProductEntity> findProducts();

    // 제품 조회 (단수)
    ProductEntity findProduct(Long productId);

    // 제품 추가
    Integer addProduct(ProductEntity product);

    // 제품 수정
    Integer updateProduct(ProductEntity product);

    // 제품 삭제
    Integer deleteProduct(Long productId);
}
