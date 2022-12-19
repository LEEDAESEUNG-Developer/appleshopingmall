package com.appleshopingmall.service;

import com.appleshopingmall.controller.web.Criteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void testPaing(){
        productService.findByProductPaging(new Criteria(2, 10)).forEach(productEntity -> System.out.println(productEntity));
    }

}