package com.appleshopingmall.order;

import com.appleshopingmall.order.dto.OrderAddDto;
import com.appleshopingmall.order.dto.OrderEntity;
import com.appleshopingmall.order.dto.OrderViewDto;
import com.appleshopingmall.shop.cart.dto.CartDto;
import com.appleshopingmall.shop.cart.CartService;
import com.appleshopingmall.shop.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final CartService cartService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public int addOrder(Long memberId, String address) {
        /**
         * 주문 로직 정리
         * 1. 현재 세션 카트에 제품을 가져옴
         * 2. 재고랑 카운트랑 비교
         * 3. 재고가 없을 경우 DB 작업 X, 있을 경우 O
         * 4. 수량 빼기 재고
         */

        // 1번 로직
        List<CartDto> findCarts = cartService.findByMemberIdCart(memberId);

        // 2번 로직
        boolean cartEmpty = findCarts.stream().filter(cart -> cart.getProductCount() > cart.getProductStock()).findFirst().isEmpty();

        // 3번 로직
        if(findCarts.size() > 0 && cartEmpty){
            orderRepository.addNumberOrder(memberId);          // 주문번호 생성
            for (CartDto cart : findCarts) {
                OrderAddDto dto = OrderAddDto.builder()
                        .productId(cart.getProductId())
                        .memberId(memberId)
                        .productCount(cart.getProductCount())
                        .productPrice(cart.getProductPrice())
                        .checkStock(false)
                        .checkShipment(false)
                        .address(address)
                        .build();
                long stock = cart.getProductStock() - cart.getProductCount();
                // 4번 로직
                productRepository.updateStock(cart.getProductId(), stock);
                orderRepository.addOrder(dto);            // 주문 테이블에 값 추가
                cartService.deleteCartID(cart.getCartId()); // 카트에 담긴 제품 삭제
                return 1;
            }
        }
        return 0;
    }

    @Override
    public List<OrderViewDto> findByMemberId(Long memberId) {
        return orderRepository.findByMemberId(memberId);
    }


    @Override
    public int deleteOrder(Long memberId) {
        return orderRepository.deleteOrder(memberId);
    }

    @Override
    public OrderViewDto findByOrderId(int orderId, Long memberId) {
        return orderRepository.findByOrderId(orderId, memberId);
    }

    @Override
    public List<OrderEntity> findByMemberIdAndOrderNumber(Long memberId, Long orderTblId) {
        return orderRepository.findByMemberIdAndOrderNumber(memberId, orderTblId);
    }

    @Override
    public int updateOrderCancel(int orderId, Long memberId) {
        OrderViewDto findOrder = orderRepository.findByOrderId(orderId, memberId);
        Long productId = findOrder.getProductId();
        int productStockCount = productRepository.findByProductIdStockCount(productId);

        int stockSum = findOrder.getProductCount() + productStockCount;

        productRepository.updateStock(productId, stockSum);

        return orderRepository.updateOrderCancel(orderId, memberId);
    }

}
