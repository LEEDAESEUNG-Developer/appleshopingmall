package com.appleshopingmall.order.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class OrderAddDto {
    private Long productId;
    private Long memberId;
    private int productCount;
    private Long productPrice;
    private boolean checkStock;
    private boolean checkShipment;
    private String address;

}
