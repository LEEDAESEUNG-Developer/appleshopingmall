package com.appleshopingmall.error.exception;

public class ProductStockErrorException extends RuntimeException {

    private static final String MESSAGE = "현재 상품이 매진 되었습니다.";

    public ProductStockErrorException(){
        super(MESSAGE);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
