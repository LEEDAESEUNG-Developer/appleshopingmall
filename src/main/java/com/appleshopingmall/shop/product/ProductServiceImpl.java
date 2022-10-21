package com.appleshopingmall.shop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<ProductEntity> findByProductColor(String productName) {
        return productRepository.findByProductColor(productName);
    }

    @Override
    public List<ProductEntity> findByProductView() {
        return productRepository.findByProductView();
    }

    @Override
    public ProductEntity findByProductNumber(int productNumber) {
        return productRepository.findByProductNumber(productNumber);
    }
    @Override
    public List<ProductEntity> findNewProduct() {
        List<ProductEntity> products = new ArrayList<>();

        products.add(productRepository.findNewMac());
        products.add(productRepository.findNewIpad());
        products.add(productRepository.findNewIphone());

        return products;
    }
}
