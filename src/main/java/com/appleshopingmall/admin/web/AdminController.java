package com.appleshopingmall.admin.web;

import com.appleshopingmall.admin.AdminService;
import com.appleshopingmall.admin.entity.AdminProductEntity;
import com.appleshopingmall.admin.entity.Order;
import com.appleshopingmall.admin.web.form.DirectType;
import com.appleshopingmall.admin.web.form.PasswordReset;
import com.appleshopingmall.admin.web.form.ProductAddForm;
import com.appleshopingmall.admin.web.form.UpdateOrderForm;
import com.appleshopingmall.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

        List<AdminProductEntity> products = adminService.findProducts();
        model.addAttribute("products", products);
        return "admin/products";
    }

    @GetMapping("product/{productId}")
    public String product(@PathVariable Long productId, Model model) {
        AdminProductEntity product = adminService.findProduct(productId);
        model.addAttribute("product", product);
        return "admin/product";
    }

    @GetMapping("product/edit/{productId}")
    public String formProduct(@PathVariable Long productId, Model model) {
        AdminProductEntity product = adminService.findProduct(productId);
        model.addAttribute("product", product);
        return "admin/formProduct";
    }

    @PostMapping("product/edit/{productId}")
    public String updateProduct(@PathVariable Long productId, @ModelAttribute AdminProductEntity product){
        product.setProductId(productId);
        log.info("product => {}", product);
        adminService.updateProduct(product);
        return "redirect:/admin/product/"+productId;
    }

    @GetMapping("product/add")
    public String addForm(Model model){
        model.addAttribute("types", DirectType.values());
        model.addAttribute("form", new ProductAddForm());
        return "/admin/add";
    }

    /**
     * 파일 업로드 처리
     * 파일 경로 /product_images/
     * 1. 타입을 선택해서 파일 경로 지정 ex) mac
     * 2. Ap을 입력 받은걸로 경로 생성 ex)m1
     * 3. 이름 입력 받음 ex)MacBook-air
     * 4. 색상 입력 받음 ex)silver
     * /product_images/mac/m1/MacBook-air-m1-silver.jpeg
     */
    @PostMapping("product/add")
    public String productAdd(@Validated @ModelAttribute("form") ProductAddForm productAddForm, BindingResult bindingResult, Model model) throws IOException {
        log.info("form => {}", productAddForm);
        if(bindingResult.hasErrors()){
            model.addAttribute("types", DirectType.values());
            return "/admin/add";
        }

        log.info("img01 => {}", productAddForm.getProductMainImg().getOriginalFilename());
        adminService.addProduct(productAddForm);
        return "redirect:/admin/products";
    }

    @GetMapping("product/delete/{productId}")
    public String productDelete(@PathVariable Long productId) {
        adminService.deleteProduct(productId);
        return "redirect:/admin/products";
    }

    // 주문
    @GetMapping("orders")
    public String orders(Model model) {
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
