package com.appleshopingmall;

import com.appleshopingmall.sessionUtill.SessionUtill;
import com.appleshopingmall.shop.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final CartService cartService;

    @GetMapping(value = {"/"})
    public String index(HttpSession httpSession, Model model) {

        if(SessionUtill.getSessionUtill().hasSession(httpSession)) {
            model.addAttribute("cartCount", cartService.getMemberCartCount(SessionUtill.getSessionUtill().getMemberID(httpSession)));
        }
        return "index";
    }
}
