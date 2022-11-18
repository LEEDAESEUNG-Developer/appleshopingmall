package com.appleshopingmall.util;

import com.appleshopingmall.shop.cart.dto.CartDto;
import com.appleshopingmall.shop.cart.CartService;
import com.appleshopingmall.shop.product.ProductService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ProductError {



    private static final ProductError instance = new ProductError();


    public ProductError(){}

    public static ProductError getInstance() {
        return instance;
    }

    /**
     * 화면 단(view)에 처리
     * @param findCart List<CartDto>
     * @param productService CartServiceImpl
     * @return 매진이면 true, 아니면 false
     */
    public static boolean hasSoldOut(List<CartDto> findCart, ProductService productService) {
        boolean isSoldOut = false;
        for (CartDto cartDto : findCart) {
            log.debug("cartdto => {}", cartDto);
            int databaseStockCount = productService.findByProductIdStockCount(cartDto.getProductId());
            if(cartDto.getProductCount() > databaseStockCount) {
                isSoldOut = true;
                break;
            }
        }
        return isSoldOut;
    }

    public static void productStockError(){}
}
