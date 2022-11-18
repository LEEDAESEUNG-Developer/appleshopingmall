package com.appleshopingmall.order;

import com.appleshopingmall.error.exception.ProductStockError;
import com.appleshopingmall.order.OrderNumber.OrderNumberEntity;
import com.appleshopingmall.SideBar;
import com.appleshopingmall.order.OrderNumber.OrderNumberService;
import com.appleshopingmall.shop.cart.dto.CartDto;
import com.appleshopingmall.util.SessionUtil;
import com.appleshopingmall.shop.cart.CartService;
import com.appleshopingmall.shop.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("shop/order")
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;
    private final ProductService productService;
    private final OrderNumberService orderNumberService;

    // Get 메소드 상품 결제 전 화면
    @GetMapping("/payment")
    public String paymentForm(HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) throws ProductStockError {

        boolean session = SessionUtil.getSessionUtil().hasSession(httpSession);
        if (!session) return "redirect:/member/login";

        Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);
        log.debug("memberId => {}", memberId);

        Integer totalPrice = cartService.getCartTotalPrice(memberId);
        List<CartDto> findCart = cartService.findByMemberIdCart(memberId); // 회원 카트를 가져오기

        log.debug("findCart => {}", findCart);

        // 제품 수량 검증
        for (CartDto cart : findCart) {
            // 카트 아이디 가지고 와서 DB 조회 stock만 반환
            if(cart.getProductCount() > cart.getProductStock()){
                String message = "현재 매진된 제품이 있습니다.";
                redirectAttributes.addFlashAttribute("isSoldOut", true);
                return "redirect:/shop/cart";
            }
        }

        model.addAttribute("totalPrice", totalPrice);
        SideBar.getInstance().modelAddCartCount(model, httpSession, cartService);
        model.addAttribute("products", findCart);
        return "/order/payment";
    }

    // 제품 구매 (form) -> 처리
    @PostMapping("/payment")
    public String payment(HttpSession httpSession, OrderEntity order){
        log.debug("order = {} ", order);

        if(!SessionUtil.getSessionUtil().hasSession(httpSession)) return "redirect:/member/login";

        order.setMemberId(SessionUtil.getSessionUtil().getMemberId(httpSession));

        orderService.addOrder(order);
        return "redirect:/shop";
    }

    @GetMapping("/{orderId}")
    public String findOrder(@PathVariable int orderId, Model model){

        OrderEntity order = orderService.findByOrderId(orderId);

        model.addAttribute("orderEntity", order);
        model.addAttribute("productService", productService);

        return "/order/product_order";
    }

    @GetMapping("/orders")
    public String orders(HttpSession httpSession, Model model){
        String url = "redirect:/member/login";
        if(SessionUtil.getSessionUtil().hasSession(httpSession)){
            url = "/order/orders";
            Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);
            List<OrderNumberEntity> orderNumbers = orderNumberService.findByMemberId(memberId);
            log.debug("OrderNumberEntity = {}", orderNumberService.findByMemberId(memberId));
            model.addAttribute("orderNumbers", orderNumbers);
            model.addAttribute("productService", productService);
            model.addAttribute("orderService", orderService);
        }
        return url;
    }
}
