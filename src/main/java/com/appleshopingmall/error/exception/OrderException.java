package com.appleshopingmall.error.exception;

public class OrderException extends RuntimeException {
    private static final String MESSAGE = "세션 또는 주문 일치 하지 않음.";

    public OrderException() {
        super(MESSAGE);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
