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
    public List<String> findByDeduplicationProduct(String column) {
        return productRepository.findByDeduplicationProduct(column);
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

        // 쿼리 파라미터 검사
        if(cri.getType() != null && cri.getAp() != null && cri.getColor() != null && !cri.getType().isBlank() && !cri.getAp().isBlank() && !cri.getColor().isBlank()){
            log.info("타입, ap, 색상");
            return productRepository.findListWithPagingTypeAndApAndColor(cri);
        } else if(cri.getType() != null && cri.getAp() != null && !cri.getType().isBlank() && !cri.getAp().isBlank()){
                log.info("타입, ap");
                return productRepository.findListWithPagingTypeAndAp(cri);
        } else if(cri.getAp() != null && cri.getColor() != null && !cri.getAp().isBlank() && !cri.getColor().isBlank()){
            log.info("ap, 색상");
            return productRepository.findListWithPagingApAndColor(cri);
        }else if(cri.getType() != null && !cri.getType().isBlank()) {
            log.info("타입");
            return productRepository.getListWithPagingType(cri);
        } else if(cri.getAp() != null && !cri.getAp().isBlank()){
            log.info("ap");
            return productRepository.findListWithPagingAp(cri);
        } else if(cri.getColor() != null && !cri.getColor().isBlank()){
            log.info("color");
            return productRepository.findListWithPagingColor(cri);
        }

        // 쿼리파라미터에 조건이 없을 경우
        log.info("조건문 끝");
        return productRepository.getListWithPaging(cri);
    }

    @Override
    public ProductEntity findByProductNumber(int productNumber) {
        return productRepository.findByProductNumber(productNumber);
    }

    @Override
    public int count(Criteria cri) {
        if(cri.getType() != null && cri.getAp() != null && cri.getColor() != null && !cri.getType().isBlank() && !cri.getAp().isBlank() && !cri.getColor().isBlank()) {
            return productRepository.countAndTypeAnyApAndColor(cri);
        } else if(cri.getType() != null && cri.getAp() != null && !cri.getType().isBlank() && !cri.getAp().isBlank()){
            return productRepository.countAndTypeAndAp(cri);
        }else if(cri.getAp() != null && cri.getColor() != null && !cri.getAp().isBlank() && !cri.getColor().isBlank()){
            log.info("ap, 색상");
            return productRepository.countAndApAndColor(cri);
        } else if(cri.getType() != null && !cri.getType().isBlank()) {
            return productRepository.countType(cri); // 타입 검색
        } else if(cri.getAp() != null && !cri.getAp().isBlank()){
            log.info("ap");
            return productRepository.countAp(cri);
        } else if(cri.getColor() != null && !cri.getColor().isBlank()){
            log.info("color");
            return productRepository.countColor(cri);
        }
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
