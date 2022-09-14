package com.appleshopingmall.shop.product;

import com.appleshopingmall.shop.ProductEntity;

public interface ProductService {

    // 제품 번호로 제품 가져오기
    ProductEntity getProduct(int productID);
}
