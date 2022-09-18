package com.appleshopingmall.shop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductEntity> findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public ProductEntity findByProductNameAndColor(String productName, String color) {
        return productRepository.findByProductNameAndColor(productName, color);
    }

}
