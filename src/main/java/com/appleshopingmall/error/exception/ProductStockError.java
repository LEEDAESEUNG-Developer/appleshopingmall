package com.appleshopingmall.error.exception;

public class ProductStockError extends Exception {

    private static final String MESSAGE = "현재 상품이 매진 되었습니다.";

    public ProductStockError(){
        super(MESSAGE);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
