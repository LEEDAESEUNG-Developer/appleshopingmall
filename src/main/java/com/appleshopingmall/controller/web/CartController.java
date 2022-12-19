package com.appleshopingmall.controller.web;

import com.appleshopingmall.SideBar;
import com.appleshopingmall.controller.web.cartForm.CartForm;
import com.appleshopingmall.error.exception.ProductStockError;
import com.appleshopingmall.entity.CartEntity;
import com.appleshopingmall.service.CartService;
import com.appleshopingmall.dto.CartAddDto;
import com.appleshopingmall.dto.CartDto;
import com.appleshopingmall.util.ProductError;
import com.appleshopingmall.util.SessionUtil;
import com.appleshopingmall.entity.ProductEntity;
import com.appleshopingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);

        SideBar.getInstance().modelAddCartCount(model, httpSession, cartService);

        List<CartDto> findCart = cartService.findByMemberIdCart(memberId);

        //매진 된 상품이 있는지 검사
        boolean isSoldOut = ProductError.hasSoldOut(findCart, productService);

        log.info("isSoldOut => {}", isSoldOut);
        log.info("cart => {}", findCart);
        model.addAttribute("isSoldOut", isSoldOut);
        model.addAttribute("cartTotal", cartService.getCartTotalPrice(memberId));
        model.addAttribute("carts", findCart);

        return "/shop/cart/cart";
    }

    /*
     * < 카트 담기 >
     * 1. 세션의 값을 가지고 카트에 담기
     * */
    @PostMapping("add/{productName}/{productColor}")
    public String addCart(@PathVariable String productName, @PathVariable String productColor, CartForm form, HttpSession httpSession, RedirectAttributes redirectAttributes) throws ProductStockError {

        if(!SessionUtil.getSessionUtil().hasSession(httpSession)) return "redirect:/member/login";

        CartAddDto cartAddDto = new CartAddDto();

        ProductEntity findProduct = productService.findByProductNameAndColor(productName, productColor);
        String message = "현재 남은 수량은 " + findProduct.getProductStock() + "개 입니다.";

        //DB에 제품 수량 보다 더 많은 제품을 담을 경우 에러처리
        if (form.getProductCount() > findProduct.getProductStock()) {
            redirectAttributes.addFlashAttribute("message", message);
            redirectAttributes.addAttribute("productName", productName);
            redirectAttributes.addAttribute("productColor", productColor);
            return "redirect:/shop/product/{productName}/{productColor}";
        }

        form.setMemberId(SessionUtil.getSessionUtil().getMemberId(httpSession));
        form.setProductId(findProduct.getProductId());
        form.setProductPrice(findProduct.getProductPrice());

        log.debug("cart = {}", form);

        cartAddDto.setMemberId(SessionUtil.getSessionUtil().getMemberId(httpSession));
        cartAddDto.setProductId(findProduct.getProductId());
        cartAddDto.setProductPrice(findProduct.getProductPrice());
        cartAddDto.setProductCount(form.getProductCount());

        cartService.addCartByMemberId(cartAddDto);
        return "redirect:/shop";
    }

    /*
     * < 카트 제품 삭제 >
     * 1. url 파라미터로 cart_id를 가지고 온다
     * 2. 가져온 card_id로 member_id랑 일치 한지 확인
     * */

    @GetMapping(value = "remove/{cartID}")
    public String deleteCart(HttpSession httpSession, @PathVariable Long cartID) {
        try {
            Long sessionMemberId = (Long) httpSession.getAttribute("memberID");
            Long cartMemberId = cartService.getMemberIDFindCardID(cartID);
            if (sessionMemberId.equals(cartMemberId)) cartService.deleteCartID(cartID);
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
//        return cartService.findMemberProductID((Long) httpSession.getAttribute("memberID"));
        return null;
    }
}