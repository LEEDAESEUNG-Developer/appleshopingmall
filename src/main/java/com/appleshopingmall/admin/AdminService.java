package com.appleshopingmall.admin;

import com.appleshopingmall.admin.entity.AdminProductEntity;
import com.appleshopingmall.admin.entity.Order;
import com.appleshopingmall.admin.web.form.ProductAddForm;
import com.appleshopingmall.admin.web.form.UpdateOrderForm;
import com.appleshopingmall.member.MemberEntity;
import com.appleshopingmall.shop.product.ProductEntity;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    // 회원

    // 회원 조회 (모두)
    List<MemberEntity> findMembers();

    // 회원 조회(단일)
    MemberEntity findMember(Long memberId);

    // 회원 삭제 (단일)
    int deleteMember(Long memberId);

    // 모든 제품 조회(복수)
    List<AdminProductEntity> findProducts();

    // 제품

    // 제품 조회 (단수)
    AdminProductEntity findProduct(Long productId);

    // 제품 추가
    int addProduct(ProductAddForm ProductAddForm) throws IOException;

    // 제품 수정
    Integer updateProduct(AdminProductEntity product);

    // 제품 삭제
    Integer deleteProduct(Long productId);

    // 주문

    // 전체 주문 조회
    List<Order> findOrders();

    // 주문 번호로 조회
    Order findOrder(Long orderId);

    // 주문번호를 가지고 출고여부, 배송여부 수정
    Integer updateOrder(Long orderId, UpdateOrderForm form);

    // 파일
}
