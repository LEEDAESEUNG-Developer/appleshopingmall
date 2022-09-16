package com.appleshopingmall.shop.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("color", productRepositoy.getProductColor(findProduct.getProductName()));
        model.addAttribute("product", findProduct);

        return "product";
    }

}
