package com.appleshopingmall.repository;

import com.appleshopingmall.repository.AdminProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminProductRepositoryTest {

    @Autowired
    AdminProductRepository adminProductRepository;
/*
    @DisplayName("모든 상품 조회")
    @Test
    void findProducts() {
        List<ProductEntity> products = adminProductRepository.findProducts();
        products.forEach(product -> System.out.println("product = " + product));
        assertThat(products).isNotEmpty();
    }

    @DisplayName("상품 조회(단일)")
    @Test
    void findProduct() {
        ProductEntity findProduct = adminProductRepository.findProduct(1L);
        System.out.println("findProduct = " + findProduct);
        assertThat(findProduct).isNotNull();
    }

    @DisplayName("상품 추가")
    @Test
    void addProduct() {
        // given
        ProductEntity product = new ProductEntity();
        product.setProductName("제품 삭제");
        product.setProductPrice(0);
        product.setProductData(new Date(System.currentTimeMillis()));
        product.setProductCategory(1);

        //when
        Integer addProduct = adminProductRepository.addProduct(null);

        //then
        assertThat(addProduct).isEqualTo(1);
    }

    @DisplayName("상품 삭제")
    @Test
    void removeProduct(){
        //when
        Long productId = null;
        List<ProductEntity> products = adminProductRepository.findProducts();

        while (productId == null) {
            for (ProductEntity product : products) {
                if(product.getProductName().equals("제품 삭제")) productId = product.getProductId();
            }
            if(productId == null) {
                addProduct();
                products = adminProductRepository.findProducts();
            }
        }

        Integer deleteProduct = adminProductRepository.deleteProduct(productId);

        // then
        Assertions.assertThat(deleteProduct).isNotEqualTo(0);
    }
    */
}