package com.appleshopingmall.admin;

import com.appleshopingmall.admin.entity.Order;
import com.appleshopingmall.admin.entity.UpdateOrderForm;
import com.appleshopingmall.member.MemberEntity;
import com.appleshopingmall.shop.product.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public String mainPage() {
        return "admin/index";
    }

    // 회원

    @GetMapping("members")
    public String members(Model model) {
        List<MemberEntity> members = adminService.findMembers();
        model.addAttribute("members", members);
        return "admin/members";
    }

    @GetMapping("member/{memberId}")
    public String formMember(@PathVariable Long memberId, Model model) {
        MemberEntity findMember = adminService.findMember(memberId);
        model.addAttribute("member", findMember);
        return "admin/member";
    }

    // 제품

    @GetMapping("products")
    public String products(Model model) {
        List<ProductEntity> products = adminService.findProducts();
        model.addAttribute("products", products);
        return "admin/products";
    }

    @GetMapping("product/{productId}")
    public String product(@PathVariable Long productId, Model model) {
        ProductEntity product = adminService.findProduct(productId);
        model.addAttribute("product", product);
        return "admin/product";
    }

    @GetMapping("product/edit/{productId}")
    public String formProduct(@PathVariable Long productId, Model model) {
        ProductEntity product = adminService.findProduct(productId);
        model.addAttribute("product", product);
        return "admin/formProduct";
    }

    @PostMapping("product/edit/{productId}")
    public String updateProduct(@PathVariable Long productId, @ModelAttribute ProductEntity product){
        product.setProductId(productId);
        log.info("product => {}", product);
        adminService.updateProduct(product);
        return "redirect:/admin/product/"+productId;
    }

    // 주문

    @GetMapping("orders")
    public String orders(Model model){
        List<Order> orders = adminService.findOrders();
        log.info("orders = {}", orders);
        model.addAttribute("orders", orders);
        return "/admin/orders";
    }

    @GetMapping("order/{orderId}")
    public String order(@PathVariable Long orderId, Model model) {

        Order order = adminService.findOrder(orderId);
        log.info("order = {}", order);

        model.addAttribute("order", order);

        return "/admin/order";
    }

    @GetMapping("order/edit/{orderId}")
    public String orderForm(@PathVariable Long orderId, Model model) {

        Order order = adminService.findOrder(orderId);
        log.info("order = {}", order);

        model.addAttribute("order", order);

        return "/admin/orderForm";
    }

    @PostMapping("order/edit/{orderId}")
    public String updateOrder(@PathVariable Long orderId, UpdateOrderForm form) {

        adminService.updateOrder(orderId, form);

        return "redirect:/admin/order/"+orderId;
    }

}
