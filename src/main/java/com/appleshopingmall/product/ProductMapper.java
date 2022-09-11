package com.appleshopingmall.product;

import com.appleshopingmall.member.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    // 전체 제품 보기
    List<ProductEntity> getProductAll();

    // 제품 번호로 제품 가져오기
    ProductEntity getProduct(int productID);

    // 회원 번호로 카트를 가져오기
    List<ProductEntity> findMemberProduct(Long memberID);

}
