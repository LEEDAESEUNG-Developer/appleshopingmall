package com.appleshopingmall.admin.entity;

import lombok.Data;

@Data
public class UpdateOrderForm {
    private Long orderId;
    private boolean checkStock;
    private boolean checkShipment;
}
