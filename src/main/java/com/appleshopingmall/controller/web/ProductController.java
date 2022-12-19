package com.appleshopingmall.controller.web;

import com.appleshopingmall.SideBar;
import com.appleshopingmall.dto.PageDTO;
import com.appleshopingmall.service.CartService;
import com.appleshopingmall.service.ProductService;
import com.appleshopingmall.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("shop")
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping
    public String shop(Model model, HttpSession httpSession, Criteria cri){

        log.info("cri = {} ", cri);

        List<ProductEntity> products = productService.findByProductPaging(cri);
        log.info("products -> {}", products);
        model.addAttribute("pageMaker", new PageDTO(cri, productService.count(cri)));

        List<ProductEntity> productListAll = productService.findAll();

        SideBar.getInstance().modelAddCartCount(model, httpSession, cartService);


        List<String> productColor = productService.findByDeduplicationProduct("product_color");
        List<String> productAp = productService.findByDeduplicationProduct("product_ap");

        model.addAttribute("colors", productColor);
        model.addAttribute("aps", productAp);
        model.addAttribute("products", products);
        model.addAttribute("productListAll", productListAll);

        return "/shop/product/shop";
    }

    @GetMapping(value = "/product/{productName}/{productColor}")
    public String productForm(@PathVariable String productName, @PathVariable String productColor, Model model) {

        ProductEntity product = productService.findByProductNameAndColor(productName, productColor);

        List<String> colors = productService.getProductColorByProductName(productName);

        model.addAttribute("product", product);
        model.addAttribute("colors", colors);

        return "/shop/product/product";
    }

}
