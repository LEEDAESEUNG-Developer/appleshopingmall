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

    @GetMapping(value = "/product/{productName}/{productColor}")
    public String product(@PathVariable String productName, @PathVariable String productColor, Model model) {
        log.debug("손님이 입장하였습니다.");
        ProductEntity product = productService.findByProductNameAndColor(productName, productColor);
        List<ProductEntity> products = productService.findByProductName(productName);
        model.addAttribute("product", product);
        model.addAttribute("products", products);

        return "product";
    }

    /*@GetMapping(value = "/product/{productID}")
    public String product(@PathVariable int productID, Model model) {
        ProductEntity findProduct = productRepository.findProductID(productID);

        log.debug("findProduct = " + findProduct);
        model.addAttribute("color", productRepository.findByProductName(findProduct.getProductName()));
        model.addAttribute("product", findProduct);

        return "product";
    }*/
/*
    @GetMapping(value = "/product/{productID}", params = "color")
    public String product(@PathVariable int productID, Model model, @RequestParam("color") String color) {

        log.debug("arg[0]={}, arg[1]={}", productID, color);

        ProductEntity findProduct = productRepository.findByColor(productID, color);

        log.debug("findProduct = " + findProduct);
        log.debug("param = " + color);
        model.addAttribute("color", productRepository.findByProductNameAndColor(findProduct.getProductName()));
        model.addAttribute("product", findProduct);

        return "product";
    }*/
}
