package com.appleshopingmall.error.exception;

public class AdminSession extends Exception {

    private static final String MESSAGE = "관리자 세션이 없는 접속자";

    public AdminSession() {
        super(MESSAGE);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
