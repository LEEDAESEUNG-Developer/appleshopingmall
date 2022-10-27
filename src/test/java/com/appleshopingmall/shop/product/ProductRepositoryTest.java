package com.appleshopingmall.shop.product;

import com.appleshopingmall.paging.Criteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("검색 type 페이징 테스트")
    void searchPagingTest(){
        Criteria criteria = new Criteria();
        criteria.setType("iPhone");
        List<ProductEntity> typeProducts = productRepository.getListWithPagingType(criteria);
        typeProducts.forEach(product -> System.out.println("typeProduct ->" + product));
        System.out.println("productRepository.count -> " +  productRepository.countType(criteria));

    }
}