package com.appleshopingmall.order.OrderNumber;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderNumberService {
    // 회원번호로 order_number 테이블 조회
    List<OrderNumberEntity> findByMemberId(@Param("memberId") Long memberId);

}
