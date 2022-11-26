package com.appleshopingmall.order.dto;

import lombok.Data;

@Data
public class OrderViewDto {
    private int orderId;
    private int orderTblId;
    private Long memberId;
    private Long productId;
    private int productCount;
    private Long productPrice;
    private boolean checkStock;
    private boolean checkShipment;
    private String address;
    private boolean cancel;
}
