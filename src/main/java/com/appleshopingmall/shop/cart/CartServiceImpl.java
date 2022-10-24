package com.appleshopingmall.shop.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<CartEntity> findMemberProductID(Long memberID) {
        return cartRepository.findMemberProductID(memberID);
    }

    @Override
    public int addCartByMemberId(CartEntity cartEntity) {
        return cartRepository.addCartByMemberId(cartEntity);
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
