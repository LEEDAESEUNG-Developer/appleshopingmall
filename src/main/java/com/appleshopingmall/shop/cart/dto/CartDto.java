package com.appleshopingmall.shop.cart.dto;

import lombok.Data;

// 카트 담긴 정보
@Data
public class CartDto {

    // 제품
    private String productName;
    private String productColor;
    private String productAP;
    private String productRam;
    private String productStorage;
    private Long productStock;
    private String productImg01;

    //카트
    private Long cartId;
    private Long memberId;
    private Long productId;
    private Long productPrice;
    private int productCount;
}
