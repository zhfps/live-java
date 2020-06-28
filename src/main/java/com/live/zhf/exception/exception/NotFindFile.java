package com.live.zhf.exception.exception;

public class NotFindFile  extends Exception {
    public NotFindFile(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFindFile(String msg) {
        super(msg);
    }
}

