package com.appleshopingmall.order;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    int addOrder(OrderEntity orderEntity);
    List<OrderEntity> findByMemberId(Long memberId);
    int deleteOrder(Long memberId);
    OrderEntity findByOrderId(int orderId);
    List<OrderEntity> findByMemberIdAndOrderNumber(Long memberId, Long orderId);

}
