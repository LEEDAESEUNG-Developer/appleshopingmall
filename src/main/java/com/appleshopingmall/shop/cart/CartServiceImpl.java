package com.appleshopingmall.shop.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepositoy cartRepositoy;

    @Override
    public List<CartEntity> findMemberProductID(Long memberID) {
        return cartRepositoy.findMemberProductID(memberID);
    }

    @Override
    public int getMemberCartCount(Long memberID) {
        return cartRepositoy.getMemberCartCount(memberID);
    }

    @Override
    public int getCartTotalPrice(Long memberID) {
        return cartRepositoy.getCartTotalPrice(memberID);
    }

    @Override
    public Long getMemberIDFindCardID(Long cartID) {
        return cartRepositoy.getMemberIDFindCardID(cartID);
    }

    @Override
    public Long deleteCartID(Long cardID) {
        return cartRepositoy.deleteCartID(cardID);
    }
}
