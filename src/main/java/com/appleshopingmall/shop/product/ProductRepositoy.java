package com.appleshopingmall.shop.product;

import com.appleshopingmall.shop.ProductEntity;

import java.util.List;

public interface ProductRepositoy {

    // 전체 제품 보기
    List<com.appleshopingmall.shop.ProductEntity> getProductAll();

    // 제품 번호로 제품 가져오기
    ProductEntity getProduct(int productID);
}
