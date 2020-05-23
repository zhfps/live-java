package com.live.zhf.exception;

public class InsertException extends Exception {
    public InsertException(String msg, Throwable t) {
        super(msg, t);
    }

    public InsertException(String msg) {
        super(msg);
    }
}