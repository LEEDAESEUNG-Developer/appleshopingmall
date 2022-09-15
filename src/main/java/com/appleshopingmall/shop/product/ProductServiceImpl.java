package com.appleshopingmall.shop.product;

import com.appleshopingmall.shop.product.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepositoy productRepositoy;
    private final ColorRepositoy colorRepositoy;

    @Override
    public ProductEntity getProduct(int productID) {
        return productRepositoy.getProduct(productID);
    }

    @Override
    public List<ColorEntity> getProductColor(Long productID) {
        return colorRepositoy.getProductColor(productID);
    }
}
