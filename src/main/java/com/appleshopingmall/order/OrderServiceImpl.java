package com.appleshopingmall.order;

import com.appleshopingmall.shop.cart.CartEntity;
import com.appleshopingmall.shop.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final CartService cartService;
    private final OrderRepository orderRepository;

    @Override
    public int addOrder(OrderEntity orderEntity) {
        int chk = 0;
        List<CartEntity> findCarts = cartService.findMemberProductID(orderEntity.getMemberId());
        if(findCarts.size() >= 1){
            orderRepository.addNumberOrder(orderEntity);          // 주문번호 생성
            findCarts.forEach(cartEntity -> {
                orderEntity.setProductId(cartEntity.getProductID());
                orderEntity.setProductCount(cartEntity.getProductCount());
                orderEntity.setProductPrice(cartEntity.getProductPrice());
                orderEntity.setCheckStock(false);
                orderEntity.setCheckShipment(false);
                orderEntity.setAddress(orderEntity.getAddress());
                orderRepository.addOrder(orderEntity);            // 주문 테이블에 값 추가
                cartService.deleteCartID(cartEntity.getCartID()); // 카트에 담긴 제품 삭제
            });
            chk = 1;
        }
        return chk;
    }

    @Override
    public List<OrderEntity> findByMemberId(Long memberId) {
        return orderRepository.findByMemberId(memberId);
    }


    @Override
    public int deleteOrder(Long memberId) {
        return orderRepository.deleteOrder(memberId);
    }

    @Override
    public OrderEntity findByOrderId(int orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderEntity> findByMemberIdAndOrderNumber(Long memberId, Long orderTblId) {
        return orderRepository.findByMemberIdAndOrderNumber(memberId, orderTblId);
    }
}
