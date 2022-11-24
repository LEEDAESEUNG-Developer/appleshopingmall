package com.appleshopingmall.error.exception;

public class EmailSessionExcepiton extends Exception {

    private static final String MESSAGE = "이메일을 다시 입력해주세요.";

    public EmailSessionExcepiton() {
        super(MESSAGE);
    }

    @Override
    public String toString() {
        return MESSAGE;
    }
}
