package com.appleshopingmall.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class AdminProductEntity {
    private Long productId;
    private String productName;
    private long productPrice;
    private Date productData;
    private int productCategory;
    private String productColor;
    private String productAP;
    private String productRam;
    private String productOperatingSystem;
    private String productStorage;
    private int productStock;
    private String productImg01;
    private String productImg02;
    private String productImg03;
    private String productImg04;
    private String productContentImg01;
}
