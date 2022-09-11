package com.appleshopingmall.product;

import com.appleshopingmall.sessionUtill.SessionUtill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    /*
    * < 회원 카트 조회 코드 >
    * 1. 회원 카트 테이블조회 (member_id컬럼)
    * 2. 제품 출력은 (prouct_id 컬럼)
    * */
    @GetMapping("cart")
    public String cart(HttpSession httpSession, Model model) {
        String url = "redirect:/member/login";

        if (SessionUtill.getSessionUtill().isSession(httpSession)) {
            url = "cart";
            model.addAttribute("cart", (List<ProductEntity>) productService.findMemberProduct((Long)httpSession.getAttribute("memberID")));
        }
        return url;
    }

    @ResponseBody
    @GetMapping("test")
    public List<ProductEntity> test(HttpSession httpSession){
        System.out.println(httpSession.getAttribute("memberID"));
//        return productService.getAllProduct();
        return productService.findMemberProduct((Long) httpSession.getAttribute("memberID"));
    }
}
