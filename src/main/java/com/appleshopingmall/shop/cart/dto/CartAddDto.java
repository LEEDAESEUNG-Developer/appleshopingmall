package com.appleshopingmall.shop.cart.dto;

import lombok.Data;

@Data
public class CartAddDto {
    private Long memberId;
    private Long productId;
    private Long productPrice;
    private int productCount;
}
