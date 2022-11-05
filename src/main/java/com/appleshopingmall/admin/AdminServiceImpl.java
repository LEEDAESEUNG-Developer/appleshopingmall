package com.appleshopingmall.admin;

import com.appleshopingmall.admin.entity.Order;
import com.appleshopingmall.admin.entity.UpdateOrderForm;
import com.appleshopingmall.admin.repository.AdminMemberRepository;
import com.appleshopingmall.admin.repository.AdminOrderRepository;
import com.appleshopingmall.admin.repository.AdminProductRepository;
import com.appleshopingmall.member.MemberEntity;
import com.appleshopingmall.order.OrderEntity;
import com.appleshopingmall.shop.product.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminMemberRepository memberRepository;
    private final AdminProductRepository productRepository;
    private final AdminOrderRepository orderRepository;

    @Override
    public List<MemberEntity> findMembers() {
        return memberRepository.findMembers();
    }

    @Override
    public MemberEntity findMember(Long memberId) {
        return memberRepository.findMember(memberId);
    }

    @Override
    public int deleteMember(Long memberId) {
        return memberRepository.deleteMember(memberId);
    }

    @Override
    public List<ProductEntity> findProducts() {
        return productRepository.findProducts();
    }

    @Override
    public ProductEntity findProduct(Long productId) {
        return productRepository.findProduct(productId);
    }

    @Override
    public Integer addProduct(ProductEntity product) {
        return productRepository.addProduct(product);
    }

    @Override
    public Integer updateProduct(ProductEntity product) {
        return productRepository.updateProduct(product);
    }

    @Override
    public Integer deleteProduct(Long productId) {
        return productRepository.deleteProduct(productId);
    }

    @Override
    public List<Order> findOrders() {
        return orderRepository.orders();
    }

    @Override
    public Integer updateOrder(Long orderId, UpdateOrderForm form) {
        Order order = new Order(orderId, form.isCheckStock(), form.isCheckShipment());
        return orderRepository.updateOrder(orderId, order);
    }

    @Override
    public Order findOrder(Long orderId) {
        return orderRepository.findOrder(orderId);
    }
}
