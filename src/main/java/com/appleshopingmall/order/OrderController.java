package com.appleshopingmall.order;

import com.appleshopingmall.order.OrderNumber.OrderNumberEntity;
import com.appleshopingmall.SideBar;
import com.appleshopingmall.order.OrderNumber.OrderNumberService;
import com.appleshopingmall.sessionUtill.SessionUtill;
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

    @GetMapping
    public String order() {
        return "";
    }

    // Get 메소드 상품 결제 전 화면
    @GetMapping("/payment")
    public String payment(HttpSession httpSession, Model model) {
        boolean session = SessionUtill.getSessionUtill().hasSession(httpSession);
        String url = "redirect:/member/login";
        if (session) {
            Long memberId = SessionUtill.getSessionUtill().getMemberID(httpSession);
            log.debug("memberId => {}", memberId);
            Integer totalPrice = cartService.getCartTotalPrice(memberId);
            model.addAttribute("totalPrice", totalPrice);
            SideBar.getInstance().modelAddCartCount(model, httpSession, cartService);
            model.addAttribute("products", cartService.findMemberProductID(memberId));
            url = "/order/payment";
        }
        return url;
    }

    // Post 메소드 상품 결제 버튼 누를경우
    @PostMapping("/payment")
    public String postPayment(HttpSession httpSession, OrderEntity order){
        log.debug("order = {} ", order);
        boolean session = SessionUtill.getSessionUtill().hasSession(httpSession);
        String url = "redirect:/member/login";
        if(session){
            order.setMemberId(SessionUtill.getSessionUtill().getMemberID(httpSession));
            orderService.addOrder(order);
            url = "redirect:/shop";
        }
        return url;
    }

    @GetMapping("/{orderId}")
    public String findOrder(@PathVariable int orderId, Model model){

        OrderEntity order = orderService.findByOrderId(orderId);

        model.addAttribute("orderEntity", order);
        model.addAttribute("productService", productService);

        return "/order/product_order";
    }

    @GetMapping("/orders")
    public String list(HttpSession httpSession, Model model){
        String url = "redirect:/member/login";
        if(SessionUtill.getSessionUtill().hasSession(httpSession)){
            url = "/order/orders";
            Long memberID = SessionUtill.getSessionUtill().getMemberID(httpSession);
            List<OrderNumberEntity> orderNumbers = orderNumberService.findByMemberId(memberID);
            log.debug("orderEntity = {}", orderNumberService.findByMemberId(memberID));
            model.addAttribute("orderNumbers", orderNumbers);
            model.addAttribute("productService", productService);
            model.addAttribute("orderService", orderService);
        }
        return url;
    }
}
