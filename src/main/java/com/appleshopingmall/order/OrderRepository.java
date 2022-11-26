package com.appleshopingmall.order;

import com.appleshopingmall.order.dto.OrderAddDto;
import com.appleshopingmall.order.dto.OrderEntity;
import com.appleshopingmall.order.dto.OrderViewDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderRepository {

    // 주문번호 추가
    int addNumberOrder(@Param("memberId") Long memberId);

    // 주문 제품 추가
    int addOrder(@Param("dto") OrderAddDto dto);

    // 회원 번호로 주문 가져오기
    List<OrderViewDto> findByMemberId(@Param("memberId") Long memberId);

    // 회원번호로 주문 테이블 모두 삭제
    int deleteOrder(@Param("memberId") Long memberId);

    // 회원번호, 주문번호로 상품 가져오기
    List<OrderEntity> findByMemberIdAndOrderNumber(@Param("memberId") Long memberId, @Param("orderTblId") Long orderTblId);

    // orderId로 order_tbl 조회
    OrderViewDto findByOrderId(@Param("orderId") int orderId, @Param("memberId") Long memberId);

    // 회원 아이디, 주문 아이디를 받아서 주문 취소 기능
    int updateOrderCancel(@Param("orderId") int orderId, @Param("memberId") Long memberId);
}
