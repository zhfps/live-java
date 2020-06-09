package com.live.zhf.exception.exception;

public class SysException extends Exception {
    public SysException(String msg, Throwable t) {
        super(msg, t);
    }

    public SysException(String msg) {
        super(msg);
    }
}
