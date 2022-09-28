package com.appleshopingmall;

import com.appleshopingmall.sessionUtill.SessionUtill;
import com.appleshopingmall.shop.cart.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final CartService cartService;

    @GetMapping(value = {"/"})
    public String index(HttpSession httpSession, Model model) {
        SideBar.getInstance().modelAddCartCount(model, httpSession, cartService);
        return "index";
    }
}
