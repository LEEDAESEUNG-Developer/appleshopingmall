package com.appleshopingmall.order;

import com.appleshopingmall.order.dto.OrderEntity;
import com.appleshopingmall.shop.cart.CartEntity;
import com.appleshopingmall.shop.cart.CartService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OrderMapperTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    private final CartEntity cartEntity  = new CartEntity();
    private final OrderEntity orderEntity = new OrderEntity();

    @BeforeEach
    void init() {
        cartEntity.setMemberId(2L);
        cartEntity.setProductId(1L);
        cartEntity.setProductPrice(1390000L);
        cartEntity.setProductCount(1);


        orderEntity.setMemberId(2L);
        orderEntity.setProductId(1L);
        orderEntity.setProductCount(1);
        orderEntity.setProductPrice(1390000L);
        orderEntity.setAddress("경기도");
    }


    /**
     * 1. 결제가 완료시 카트에 있는 제품들을 주문에 추가함
     * 2. 주문 완료시 카트에 있는 제품 삭제
     */

   /* @Order(1)
    @DisplayName("주문 추가_성공")
    @Test
    void addOrder_O() {
        cartService.addCartByMemberId(null);          // 카트 추가
        int addOrder = orderService.addOrder(orderEntity);  // 주문 추가

        List<OrderEntity> findOrder = orderService.findByMemberId(2L); // 검색
        System.out.println("findOrder = " + findOrder);

        *//* 둘이 비교해야하지만 귀찮아서 구현하지 않음*//*

        Assertions.assertEquals(addOrder, 1);
    }*/

    @Order(1)
    @DisplayName("주문 추가_실패")
    @Test
    void addOrder_X() {
/*        OrderEntity order = new OrderEntity();
        order.setMemberId(2L);
        int addOrder = orderService.addOrder(order);

        List<OrderEntity> findOrder = orderService.findByMemberId(2L);
        System.out.println("findOrder = " + findOrder);

        Assertions.assertNotEquals(addOrder, 0);*/
    }

    @Order(2)
    @DisplayName("주문 확인")
    @Test
    void orderCheck() {
        List<OrderEntity> byMemberId = orderService.findByMemberId(2L);
        Assertions.assertNotEquals(byMemberId.size(), 0);
        System.out.println(byMemberId);
    }

    @Order(10)
    @DisplayName("주문 목록이 있을 경우 삭제")
    @Test
    void deleteOrder(){
        int deleteOrder = orderService.deleteOrder(2L);

        Assertions.assertNotEquals(deleteOrder, 0);
    }
}