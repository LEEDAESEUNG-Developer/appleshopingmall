package com.appleshopingmall.shop.product;

import com.appleshopingmall.shop.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("shop")
public class ProductController {

    private final ProductRepositoy productRepositoy;

    @GetMapping(value="/product/{productID}")
    public String product(@PathVariable int productID, Model model){

        ProductEntity findProduct = productRepositoy.getProduct(productID);

        log.debug("findProduct = " + findProduct);
        model.addAttribute("product", findProduct);

        return "product";
    }

}
