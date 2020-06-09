package com.live.zhf.exception.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaExcetion extends AuthenticationException {
    public CaptchaExcetion(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaExcetion(String msg) {
        super(msg);
    }
}
