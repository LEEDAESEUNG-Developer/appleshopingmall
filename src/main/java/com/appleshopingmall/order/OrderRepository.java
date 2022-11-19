package com.appleshopingmall.order;

import com.appleshopingmall.order.dto.OrderAddDto;
import com.appleshopingmall.order.dto.OrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderRepository {

    // 주문번호 추가
    int addNumberOrder(@Param("memberId") Long memberId);

    // 주문 제품 추가
    int addOrder(@Param("dto") OrderAddDto dto);

    // 회원 번호로 주문 가져오기
    List<OrderEntity> findByMemberId(@Param("memberId") Long memberId);

    // 회원번호로 주문 테이블 모두 삭제
    int deleteOrder(@Param("memberId") Long memberId);

    // 회원번호, 주문번호로 상품 가져오기
    List<OrderEntity> findByMemberIdAndOrderNumber(@Param("memberId") Long memberId, @Param("orderTblId") Long orderTblId);

    // orderId로 order_tbl 조회
    OrderEntity findByOrderId(@Param("orderId") int orderId);

    /**
     * 제품 아이디에 재고 수정
     * @param productId 제품 아이디
     * @param stock 제품 재고
     * @return 성공시 1, 실패시 0
     */
    int updateStock(@Param("productId") Long productId, @Param("stock") long stock);
}
