package com.appleshopingmall.order.OrderNumber;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderNumberEntity {
    private int orderNumberId;
    private int memberId;
    private Timestamp orderDate;
}
