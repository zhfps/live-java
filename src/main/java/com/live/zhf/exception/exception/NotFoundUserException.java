package com.live.zhf.exception.exception;

import org.springframework.security.core.AuthenticationException;

public class NotFoundUserException extends AuthenticationException {
    public NotFoundUserException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundUserException(String msg) {
        super(msg);
    }
}
