package com.live.zhf.exception.exception;

public class CodeException extends Exception {
    public CodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public CodeException(String msg) {
        super(msg);
    }
}
