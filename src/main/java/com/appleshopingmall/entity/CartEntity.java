package com.appleshopingmall.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CartEntity {
    private long productId;
    private String productName;
    private long productPrice;
    private Timestamp productData;
    private int productCategory;
    private String productColor;
    private String productAP;
    private String productRam;
    private String productStorage;
    private String productImg01;
    private Long cartId;
    private Long memberId;
    private int productCount;
}
