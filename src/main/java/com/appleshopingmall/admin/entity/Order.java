package com.appleshopingmall.admin.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Order {
    private Long orderId;
    private Long orderGroupNo;
    private String memberName;
    private String productName;
    private String productColor;
    private String productAP;
    private String productStorage;
    private Long productCount;
    private long productPrice;
    private boolean checkStock;
    private boolean checkShipment;
    private Timestamp orderDate;
    private String productImg01;

    public Order() {
    }

    // Update Form Constructor
    public Order(Long orderId, boolean checkStock, boolean checkShipment) {
        this.orderId = orderId;
        this.checkStock = checkStock;
        this.checkShipment = checkShipment;
    }
}
