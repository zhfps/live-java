package com.live.zhf.exception.exception;

public class DeleteException extends Exception {
    public DeleteException(String msg, Throwable t) {
        super(msg, t);
    }

    public DeleteException(String msg) {
        super(msg);
    }
}