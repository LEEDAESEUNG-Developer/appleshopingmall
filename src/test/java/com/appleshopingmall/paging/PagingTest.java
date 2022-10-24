package com.appleshopingmall.paging;

import com.appleshopingmall.shop.product.ProductEntity;
import com.appleshopingmall.shop.product.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PagingTest {

    @Autowired
    private ProductMapper productRepository;

    @Test
    public void testPaing(){
        Criteria cri = new Criteria();
        List<ProductEntity> list = productRepository.getListWithPaging(cri);
        list.forEach(productEntity -> System.out.println(productEntity));
    }
}
