package com.appleshopingmall.shop.product;

import com.appleshopingmall.shop.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepositoy productRepositoy;

    @Override
    public ProductEntity getProduct(int productID) {
        return productRepositoy.getProduct(productID);
    }
}
