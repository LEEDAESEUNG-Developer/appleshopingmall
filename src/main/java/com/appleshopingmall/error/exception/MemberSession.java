package com.appleshopingmall.error.exception;

public class MemberSession extends Exception {

    private static final String MESSAGE = "비 로그인자 접속";

    public MemberSession() {
        super(MESSAGE);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
