package com.appleshopingmall.service;

import com.appleshopingmall.controller.web.adminForm.DirectType;
import com.appleshopingmall.controller.web.adminForm.ProductAddForm;
import com.appleshopingmall.service.AdminServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AdminServiceImplTest {

    AdminServiceImpl adminService = new AdminServiceImpl(null, null, null, null);


    @DisplayName("경로 테스트")
    @Test
    void urlTest(){
        // given 주어졌을 때
        ProductAddForm productAddForm = new ProductAddForm();
        productAddForm.setProductName("MacBook-Air");
        productAddForm.setSeries("4");
        productAddForm.setProductAP("m4");
        productAddForm.setDirectType(DirectType.MacBook);
        productAddForm.setProductColor("silver");

        // when 수행 했을 때
        String filename = adminService.productMainName(productAddForm);

        // then 이렇게 되야함
        assertThat(filename).isEqualTo("MacBook/4/MacBook-Air-m4-silver");
    }
}