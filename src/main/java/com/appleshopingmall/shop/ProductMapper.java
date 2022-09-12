package com.appleshopingmall.shop;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    // 전체 제품 보기
    List<ProductEntity> getProductAll();

    // 제품 번호로 제품 가져오기
    ProductEntity getProduct(int productID);

    // 회원 번호로 장바구니 가지고 오기
    List<CartEntity> findMemberProductID(Long productID);

    // 회원 아이디로 장바구니 수량 가져옴
    int getMemberCartCount(Long memberID);
}
