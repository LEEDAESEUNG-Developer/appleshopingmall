package com.appleshopingmall.order.dto;

import lombok.Data;

@Data
public class OrderEntity {

    /**
     * 주문 서비스 프로세스
     * 1. 상품이 있는지 확인
     * 2. 카트에 담겨져있는 총 결제 금액을 전달함
     * 3. 결제 성공시 카트아이디를 주문 테이블에 넘김
     * 4. 물류쪽에서 상품을 회원 주소로 보냄
     * 5. 택배사에 도착
     */
    private int orderId;
    private int orderTblId;
    private Long memberId;
    private Long productId;
    private int productCount;
    private Long productPrice;
    private boolean checkStock;
    private boolean checkShipment;
    private String address;
}
