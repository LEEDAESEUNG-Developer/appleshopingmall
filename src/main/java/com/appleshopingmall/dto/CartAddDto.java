package com.appleshopingmall.dto;

import lombok.Data;

@Data
public class CartAddDto {
    private Long memberId;
    private Long productId;
    private Long productPrice;
    private int productCount;
}
