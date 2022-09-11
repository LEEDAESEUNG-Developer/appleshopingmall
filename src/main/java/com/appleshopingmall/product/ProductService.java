package com.appleshopingmall.product;

import com.appleshopingmall.member.MemberEntity;

import java.util.List;

public interface ProductService {

    // 전체 제품 가져오기
    List<ProductEntity> getAllProduct();

    // 재품번호로 제품을 가져오기
    ProductEntity getProduct(int productID);

    // 회원 번호로 카트에 담긴 제품을 가져오기
    List<ProductEntity> findMemberProduct(Long memberID);

}
