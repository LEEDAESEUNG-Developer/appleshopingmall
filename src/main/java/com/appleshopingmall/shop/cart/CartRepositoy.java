package com.appleshopingmall.shop.cart;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartRepositoy {
    // 회원 번호로 장바구니 가지고 오기
    List<CartEntity> findMemberProductID(Long productID);

    // 회원 아이디로 제품을 장바구니 추가 성공시 1, 실패시 0
    int addCartByMemberId(@Param("cartEntity") CartEntity cartEntity);

    // 회원 아이디로 장바구니 수량 가져옴
    Integer getMemberCartCount(@Param("memberID") Long memberID);

    // 회원 아이디로 장바구니에 담긴 총 금액
    Integer getCartTotalPrice(@Param("memberID") Long memberID);

    // 카트 아이디로 회원 번호 가지고 오기
    Long getMemberIDFindCardID(@Param("cartID") Long cartID);

    // 카트 아이디로 카트 삭제
    Long deleteCartID(@Param("cartID") Long cartID);

}
