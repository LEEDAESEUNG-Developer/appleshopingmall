package com.appleshopingmall.shop.cart;

import com.appleshopingmall.shop.cart.dto.CartAddDto;
import com.appleshopingmall.shop.cart.dto.CartDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartService {

    // 회원 번호로 카트에 담긴 제품을 가져오기
    List<CartDto> findByMemberIdCart(Long memberID);

    // 회원 아이디로 제품을 장바구니 추가 성공시 1, 실패시 0
    int addCartByMemberId(CartAddDto dto);

    // 회원 아이디로 장바구니 수량 가져옴
    Integer getMemberCartCount(Long memberID);

    // 회원번호로 장바구니에 담긴 총 금액
    Integer getCartTotalPrice(Long memberID);

    // 카트 아이디로 회원 번호 가지고 오기
    Long getMemberIDFindCardID(Long cartID);

    // 카트 아이디로 카트 삭제
    Long deleteCartID(Long cardID);

}
