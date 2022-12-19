package com.appleshopingmall.repository;

import com.appleshopingmall.entity.AdminOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminOrderRepository {

    // 전체 주문 조회
    List<AdminOrder> orders();

    // 주문 번호로 조회
    AdminOrder findOrder(Long orderId);

    // 주문번호를 가지고 출고여부, 배송여부 수정
    Integer updateOrder(@Param("orderId") Long orderId, @Param("form") AdminOrder form);
}
