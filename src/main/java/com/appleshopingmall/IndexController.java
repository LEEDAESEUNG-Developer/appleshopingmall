package com.appleshopingmall;

import com.appleshopingmall.sessionUtill.SessionUtill;
import com.appleshopingmall.shop.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final ProductService productService;

    @GetMapping(value = {"/"})
    public String index(HttpSession httpSession, Model model) {

        if(SessionUtill.getSessionUtill().isSession(httpSession)) {
            model.addAttribute("cartCount", productService.getMemberCartCount(SessionUtill.getSessionUtill().getMemberID(httpSession)));
        }
        return "index";
    }
}
