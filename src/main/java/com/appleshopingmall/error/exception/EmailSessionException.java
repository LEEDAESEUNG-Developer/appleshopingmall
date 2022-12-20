package com.appleshopingmall.error.exception;

public class EmailSessionException extends RuntimeException {

    private static final String MESSAGE = "이메일을 다시 입력해주세요.";

    public EmailSessionException() {
        super(MESSAGE);
    }

    @Override
    public String toString() {
        return MESSAGE;
    }
}
