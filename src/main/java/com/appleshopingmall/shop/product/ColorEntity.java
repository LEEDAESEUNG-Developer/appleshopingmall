package com.appleshopingmall.shop.product;

import lombok.Data;

@Data
public class ColorEntity {
    private Long colorID;
    private Long productID;
    private String productColor;
}
