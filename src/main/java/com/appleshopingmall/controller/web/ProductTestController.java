package com.appleshopingmall.controller.web;

import com.appleshopingmall.dto.PageDTO;
import com.appleshopingmall.service.ProductService;
import com.appleshopingmall.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("test/shop")
@AllArgsConstructor
public class ProductTestController {

    private final ProductService productService;

    @GetMapping
    public String shop(Criteria cri, Model model){
        List<ProductEntity> productListAll = productService.findAll();

        log.info("list => {} ", productService.findByProductPaging(cri));
        model.addAttribute("products", productService.findByProductPaging(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, 500));
        model.addAttribute("productListAll", productListAll);

        return "test/product/product_paging";
    }
}
