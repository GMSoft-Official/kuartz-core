package com.kuartz.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletRequest;

public abstract class KuartzService {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected MessageSource messageSource;

    protected String getMessage(String label, Object... arguments) {
        return messageSource.getMessage(label, arguments, request.getLocale());
    }

    protected String getMessage(String label) {
        return messageSource.getMessage(label, null, request.getLocale());
    }

}
