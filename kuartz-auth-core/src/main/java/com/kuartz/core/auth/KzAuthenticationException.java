package com.kuartz.core.auth;

import com.kuartz.core.common.exception.ExceptionMessage;
import org.springframework.security.core.AuthenticationException;

public class KzAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = 477248794744780677L;

    private ExceptionMessage exceptionMessage;

    public KzAuthenticationException(String msg, Throwable t) {
        super(msg, t);
        this.exceptionMessage = new ExceptionMessage(msg);
    }

    public KzAuthenticationException(String msg) {
        super(msg);
        this.exceptionMessage = new ExceptionMessage(msg);
    }
}
