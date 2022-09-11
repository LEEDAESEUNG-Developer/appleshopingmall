package com.appleshopingmall.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public List<ProductEntity> getAllProduct() {
        return productMapper.getProductAll();
    }

    @Override
    public ProductEntity getProduct(int productID) {
        return productMapper.getProduct(productID);
    }

    @Override
    public List<ProductEntity> findMemberProduct(Long memberID) {
        return productMapper.findMemberProduct(memberID);
    }
}
