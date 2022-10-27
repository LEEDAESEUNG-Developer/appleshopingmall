package com.appleshopingmall.shop.product;

import com.appleshopingmall.paging.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
    public List<ProductEntity> findByProductPaging(Criteria cri) {
        log.info("get List with criteria " + cri);
        if(cri.getType() != null) return productRepository.getListWithPagingType(cri);
        return productRepository.getListWithPaging(cri);
    }

    @Override
    public ProductEntity findByProductNumber(int productNumber) {
        return productRepository.findByProductNumber(productNumber);
    }

    @Override
    public int count(Criteria cri) {
        if(cri.getType() != null)  return productRepository.countType(cri);
        return productRepository.count();
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
