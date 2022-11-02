package com.appleshopingmall.shop.product;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductEntity {
    private Long productID;
    private String productName;
    private long productPrice;
    private Timestamp productData;
    private int productCategory;
    private String productColor;
    private String productAP;
    private String productRam;
    private String productOperatingSystem;
    private String productStorage;
    private String productImg01;
    private String productImg02;
    private String productImg03;
    private String productImg04;
    private String productContentImg01;
}
