package com.appleshopingmall.service;

import com.appleshopingmall.repository.CartRepository;
import com.appleshopingmall.dto.CartAddDto;
import com.appleshopingmall.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<CartDto> findByMemberIdCart(Long memberID) {
        return cartRepository.findByMemberIdCart(memberID);
    }

    @Override
    public int addCartByMemberId(CartAddDto dto) {
        return cartRepository.addCartByMemberId(dto);
    }

    @Override
    public Integer getMemberCartCount(Long memberID) {
        return cartRepository.getMemberCartCount(memberID);
    }

    @Override
    public Integer getCartTotalPrice(Long memberID) {
        return cartRepository.getCartTotalPrice(memberID);
    }

    @Override
    public Long getMemberIDFindCardID(Long cartID) {
        return cartRepository.getMemberIDFindCardID(cartID);
    }

    @Override
    public Long deleteCartID(Long cardID) {
        return cartRepository.deleteCartID(cardID);
    }

}
