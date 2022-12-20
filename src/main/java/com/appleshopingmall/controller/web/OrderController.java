package com.appleshopingmall.controller.web;

import com.appleshopingmall.controller.web.orderForm.OrderAddForm;
import com.appleshopingmall.error.exception.OrderException;
import com.appleshopingmall.error.exception.ProductStockErrorException;
import com.appleshopingmall.entity.OrderEntity;
import com.appleshopingmall.entity.OrderNumberEntity;
import com.appleshopingmall.SideBar;
import com.appleshopingmall.service.OrderNumberService;
import com.appleshopingmall.service.OrderService;
import com.appleshopingmall.dto.OrderViewDto;
import com.appleshopingmall.dto.CartDto;
import com.appleshopingmall.util.SessionUtil;
import com.appleshopingmall.service.CartService;
import com.appleshopingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    public String paymentForm(HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) throws ProductStockErrorException {

        // 세션 검사
        boolean session = SessionUtil.getSessionUtil().hasSession(httpSession);
        if (!session) return "redirect:/member/login";

        Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);
        log.debug("memberId => {}", memberId);

        Integer totalPrice = cartService.getCartTotalPrice(memberId);   // 카트에 있는 제품 총 결제
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
        SideBar.getInstance().modelAddCartCount(model, httpSession, cartService);

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("products", findCart);
        model.addAttribute("form", new OrderAddForm());
        return "/order/payment";
    }

    // 제품 구매 (form) -> 처리
    @PostMapping("/payment")
    public String payment(HttpSession httpSession, OrderEntity order, @ModelAttribute("form") @Validated OrderAddForm form, BindingResult bindingResult, Model model){
        log.debug("form = {} ", form);

        // 세션 검사
        if(!SessionUtil.getSessionUtil().hasSession(httpSession)) return "redirect:/member/login";

        // 검증
        Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);
        /*if(bindingResult.hasErrors()){
            log.debug("bindingResult => {}", bindingResult);

            Integer totalPrice = cartService.getCartTotalPrice(memberId);   // 카트에 있는 제품 총 결제
            List<CartDto> findCart = cartService.findByMemberIdCart(memberId); // 회원 카트를 가져오기

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("products", findCart);
            return "order/payment";
        }*/

        orderService.addOrder(memberId, form.getAddress());
        return "redirect:/shop";
    }

    @GetMapping("/{orderId}")
    public String findOrder(@PathVariable int orderId, Model model, @SessionAttribute(SessionUtil.MEMBER_ID) Long memberId){

        OrderViewDto dto = orderService.findByOrderId(orderId, memberId);

        model.addAttribute("dto", dto);
        model.addAttribute("productService", productService);

        return "/order/product_order";
    }

    @GetMapping("/{orderId}/delete")
    public String orderDelete(@PathVariable int orderId, @SessionAttribute(SessionUtil.MEMBER_ID) Long memberId, RedirectAttributes redirectAttributes) {

        log.debug("orderDelete 메소드");

        int orderCancelResult = orderService.updateOrderCancel(orderId, memberId);
        if(orderCancelResult == 0){
            throw new OrderException();
        }

        redirectAttributes.addAttribute("orderId", orderId);
        return "redirect:/shop/order/{orderId}";
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
