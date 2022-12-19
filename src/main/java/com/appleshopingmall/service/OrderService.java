package com.appleshopingmall.service;

import com.appleshopingmall.entity.OrderEntity;
import com.appleshopingmall.dto.OrderViewDto;

import java.util.List;

public interface OrderService {

    int addOrder(Long memberId, String address);

    List<OrderViewDto> findByMemberId(Long memberId);

    int deleteOrder(Long memberId);

    OrderViewDto findByOrderId(int orderId, Long memberId);

    List<OrderEntity> findByMemberIdAndOrderNumber(Long memberId, Long orderId);

    /**
     * 1. 회원 아이디, 주문 아이디를 받아서 주문 취소 기능
     * 2. 취소 상품을 DB에 반환
     *  1. 주문 상품을 가져옴
     *  2. 주문 상품과 DB 상품의 수량을 더함
     *  3. DB에 반환
     *
     * @param orderId int
     * @param memberId Long
     * @return 수정 실패 0, 성공 1
     */
    int updateOrderCancel(int orderId, Long memberId);

}
