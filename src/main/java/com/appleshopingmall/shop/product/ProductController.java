package com.appleshopingmall.shop.product;

import com.appleshopingmall.SideBar;
import com.appleshopingmall.paging.Criteria;
import com.appleshopingmall.paging.PageDTO;
import com.appleshopingmall.shop.cart.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
