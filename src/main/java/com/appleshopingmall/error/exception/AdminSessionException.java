package com.appleshopingmall.error.exception;

public class AdminSessionException extends RuntimeException {

    private static final String MESSAGE = "관리자 세션이 없는 접속자";

    public AdminSessionException() {
        super(MESSAGE);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
