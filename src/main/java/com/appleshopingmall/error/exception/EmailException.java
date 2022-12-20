package com.appleshopingmall.error.exception;

public class EmailException extends RuntimeException{

    private static final String MESSAGE = "이메일 주소가 아닙니다.";

    public EmailException() {
        super(MESSAGE);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
