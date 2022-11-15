package com.appleshopingmall.admin.web.form;

import lombok.Data;

@Data
public class UpdateOrderForm {
    private Long orderId;
    private boolean checkStock;
    private boolean checkShipment;
}
