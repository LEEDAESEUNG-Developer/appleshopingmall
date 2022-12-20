package com.appleshopingmall.error.exception;

public class MemberSessionException extends RuntimeException {

    private static final String MESSAGE = "비 로그인자 접속";

    public MemberSessionException() {
        super(MESSAGE);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
