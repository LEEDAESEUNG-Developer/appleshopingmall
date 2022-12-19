package com.appleshopingmall.repository;

import com.appleshopingmall.controller.web.Criteria;
import com.appleshopingmall.entity.ProductEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    //    @Test
//    @DisplayName("검색 type 페이징 테스트")
    void searchPagingTest() {
        Criteria criteria = new Criteria();
        criteria.setType("iPhone");
        List<ProductEntity> typeProducts = productRepository.getListWithPagingType(criteria);
        typeProducts.forEach(product -> System.out.println("typeProduct ->" + product));
        System.out.println("productRepository.count -> " + productRepository.countType(criteria));
    }

    @DisplayName("중복검색")
    @Test
    void distinct(){
        List<String> product_color = productRepository.findByDeduplicationProduct("product_color");
        System.out.println("product_color" + product_color);

    }
}