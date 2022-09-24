package com.appleshopingmall.shop.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("shop")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String shop(Model model){
        List<ProductEntity> products = productService.findByProductView();
        List<ProductEntity> productListAll = productService.findAll();

        model.addAttribute("products", products);
        model.addAttribute("productListAll", productListAll);

        return "shop";
    }

    @GetMapping(value = "/product/{productName}/{productColor}")
    public String product(@PathVariable String productName, @PathVariable String productColor, Model model) {
        ProductEntity product = productService.findByProductNameAndColor(productName, productColor);
        List<ProductEntity> products = productService.findByProductName(productName);

        model.addAttribute("product", product);
        model.addAttribute("products", products);

        return "product";
    }

}
