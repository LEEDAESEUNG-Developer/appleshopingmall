package com.appleshopingmall.shop;

import java.util.List;

public interface ProductService {

    // 전체 제품 가져오기
    List<ProductEntity> getAllProduct();

    // 재품번호로 제품을 가져오기
    ProductEntity getProduct(int productID);

    // 회원 번호로 카트에 담긴 제품을 가져오기
    List<CartEntity> findMemberProductID(Long memberID);

    // 회원 아이디로 장바구니 수량 가져옴
    int getMemberCartCount(Long memberID);

}
