package com.live.zhf.exception.exception;

public class UpdateException extends Exception {
    public UpdateException(String msg, Throwable t) {
        super(msg, t);
    }

    public UpdateException(String msg) {
        super(msg);
    }
}