package com.appleshopingmall.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderNumberEntity {
    private int orderNumberId;
    private int memberId;
    private Timestamp orderDate;
}
