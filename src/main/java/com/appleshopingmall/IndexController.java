package com.appleshopingmall;

import com.appleshopingmall.service.CartService;
import com.appleshopingmall.entity.ProductEntity;
import com.appleshopingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping(value = {"/"})
    public String index(HttpSession httpSession, Model model) {
        SideBar.getInstance().modelAddCartCount(model, httpSession, cartService);
        List<ProductEntity> products = productService.findNewProduct();

        model.addAttribute("products", products);
        return "index";
    }
}
