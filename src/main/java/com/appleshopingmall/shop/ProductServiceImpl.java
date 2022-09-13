package com.appleshopingmall.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public List<ProductEntity> getAllProduct() {
        return productMapper.getProductAll();
    }

    @Override
    public ProductEntity getProduct(int productID) {
        return productMapper.getProduct(productID);
    }

    @Override
    public List<CartEntity> findMemberProductID(Long memberID) {
        return productMapper.findMemberProductID(memberID);
    }

    @Override
    public int getMemberCartCount(Long memberID) {
        return productMapper.getMemberCartCount(memberID);
    }

    @Override
    public int getCartTotalPrice(Long memberID) {
        return productMapper.getCartTotalPrice(memberID);
    }

    @Override
    public Long getMemberIDFindCardID(Long cartID) {
        return productMapper.getMemberIDFindCardID(cartID);
    }

    @Override
    public Long deleteCartID(Long cardID) {
        return productMapper.deleteCartID(cardID);
    }
}
