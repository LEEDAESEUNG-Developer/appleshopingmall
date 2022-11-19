package com.appleshopingmall.order;

import com.appleshopingmall.order.dto.OrderEntity;
import com.appleshopingmall.order.web.form.OrderAddForm;

import java.util.List;

public interface OrderService {
    int addOrder(Long memberId, String address);
    List<OrderEntity> findByMemberId(Long memberId);
    int deleteOrder(Long memberId);
    OrderEntity findByOrderId(int orderId);
    List<OrderEntity> findByMemberIdAndOrderNumber(Long memberId, Long orderId);

}
