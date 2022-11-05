package com.appleshopingmall.admin.repository;

import com.appleshopingmall.admin.entity.Order;
import com.appleshopingmall.admin.entity.UpdateOrderForm;
import com.appleshopingmall.order.OrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminOrderRepository {

    // 전체 주문 조회
    List<Order> orders();

    // 주문 번호로 조회
    Order findOrder(Long orderId);

    // 주문번호를 가지고 출고여부, 배송여부 수정
    Integer updateOrder(@Param("orderId") Long orderId, @Param("form") Order form);
}
