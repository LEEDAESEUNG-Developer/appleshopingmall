package com.appleshopingmall.shop.product;

import java.util.List;

public interface ColorRepositoy {
    /* 제품 번호로 색상을 가져옴*/
    List<ColorEntity> getProductColor(Long productID);
}
