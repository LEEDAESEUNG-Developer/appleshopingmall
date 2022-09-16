package com.appleshopingmall.shop.product;

import java.util.List;

public interface ProductService {

    // 제품 번호로 제품 가져오기
    ProductEntity getProduct(int productID);

    // 제품번호로 색을 가져옴
    List<ProductEntity> getProductColor(String productName);
}
