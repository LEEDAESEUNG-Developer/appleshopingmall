package com.appleshopingmall.shop.product;

import java.util.List;

public interface ProductRepositoy {

    // 전체 제품 보기
    List<ProductEntity> getProductAll();

    // 제품 번호로 제품 가져오기
    ProductEntity getProduct(int productID);

    // 제품번호로 색을 가져옴
    List<ProductEntity> getProductColor(String productName);
}
