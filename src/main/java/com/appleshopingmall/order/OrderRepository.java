package com.appleshopingmall.order;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderRepository {

    // 주문번호 추가
    int addNumberOrder(@Param("orderEntity") OrderEntity orderEntity);

    // 주문 제품 추가
    int addOrder(@Param("orderEntity")OrderEntity orderEntity);

    // 회원 번호로 주문 가져오기
    List<OrderEntity> findByMemberId(@Param("memberId") Long memberId);

    // 회원번호로 주문 테이블 모두 삭제
    int deleteOrder(@Param("memberId") Long memberId);

    // 회원번호, 주문번호로 상품 가져오기
    List<OrderEntity> findByMemberIdAndOrderNumber(@Param("memberId") Long memberId, @Param("orderTblId") Long orderTblId);

    // orderId로 order_tbl 조회
    OrderEntity findByOrderId(@Param("orderId") int orderId);

}
