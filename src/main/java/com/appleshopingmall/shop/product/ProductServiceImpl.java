package com.appleshopingmall.shop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepositoy productRepositoy;

    @Override
    public ProductEntity getProduct(int productID) {
        return productRepositoy.getProduct(productID);
    }

    @Override
    public List<ProductEntity> getProductColor(String productName) {
        return productRepositoy.getProductColor(productName);
    }
}
