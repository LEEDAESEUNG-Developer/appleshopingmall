package com.appleshopingmall.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class AdminProductAddDTO {
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

    private String productMainImg;
    private String productTopImg;
    private String productLeftImg;
    private String productRightImg;
    private String productContentImg01;

}
