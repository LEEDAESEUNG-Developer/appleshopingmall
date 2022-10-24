package com.appleshopingmall.shop.product;

import com.appleshopingmall.paging.Criteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void testPaing(){
        productService.findByProductPaging(new Criteria(2, 10)).forEach(productEntity -> System.out.println(productEntity));
    }

}