package com.appleshopingmall.shop.cart;

import com.appleshopingmall.sessionUtill.SessionUtill;
import com.appleshopingmall.shop.product.ProductEntity;
import com.appleshopingmall.shop.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("shop")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    /*
    * < 회원 카트 조회 코드 >
    * 1. 회원 카트 테이블조회 (member_id컬럼)
    * 2. 제품 출력은 (prouct_id 컬럼)
    * */
    @GetMapping("cart")
    public String cart(HttpSession httpSession, Model model) {
        String url = "redirect:/member/login";

        if (SessionUtill.getSessionUtill().hasSession(httpSession)) {
            url = "cart";
            Long memberID = (Long) httpSession.getAttribute("memberID");

            model.addAttribute("cartCount", cartService.getMemberCartCount(memberID));
            model.addAttribute("cartTotal", cartService.getCartTotalPrice(memberID));
            model.addAttribute("cart", cartService.findMemberProductID(memberID));

        }
        return url;
    }

    /*
     * < 카트 담기 >
     * 1. 세션의 값을 가지고 카트에 담기
     * */
    @PostMapping("add/{productName}/{productColor}")
    public String addCart(@PathVariable String productName, @PathVariable String productColor, CartEntity cart, HttpSession httpSession) {
        String url = "redirect:/shop";

        if(!SessionUtill.getSessionUtill().hasSession(httpSession)) url = "redirect:/member/login";

        ProductEntity findProduct = productService.findByProductNameAndColor(productName, productColor);

        cart.setMemberID((Long)httpSession.getAttribute("memberID"));
        cart.setProductID(findProduct.getProductID());
        cart.setProductPrice(findProduct.getProductPrice());

        log.debug("cart = {}", cart);

        cartService.addCartByMemberId(cart);
        return url;
    }

    /*
     * < 카트 제품 삭제 >
     * 1. url 파라미터로 cart_id를 가지고 온다
     * 2. 가져온 card_id로 member_id랑 일치 한지 확인
     * */

    @GetMapping(value = "remove/{cartID}")
    public String remove(HttpSession httpSession, @PathVariable Long cartID) {
        try {
            if ((Long) httpSession.getAttribute("memberID") == cartService.getMemberIDFindCardID(cartID)) {
                cartService.deleteCartID(cartID);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/shop/cart";
    }

    @ResponseBody
    @GetMapping("test")
    public List<CartEntity> test(HttpSession httpSession){
        System.out.println(httpSession.getAttribute("memberID"));
//        return productService.getAllProduct();
        return cartService.findMemberProductID((Long) httpSession.getAttribute("memberID"));
    }
}
