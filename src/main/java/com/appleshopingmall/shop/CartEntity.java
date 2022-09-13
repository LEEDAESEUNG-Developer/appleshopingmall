package com.appleshopingmall.shop;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CartEntity {
    private long productID;
    private String productName;
    private long productPrice;
    private Timestamp productData;
    private int productCategory;
    private String productAP;
    private String productRam;
    private String productStorage;
    private String productImg01;
    private Long cartID;
    private Long memberID;
    private int productCount;
}
