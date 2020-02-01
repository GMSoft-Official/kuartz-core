package com.kuartz.core.rest.handler;

import com.kuartz.core.common.exception.ExceptionMessage;
import com.kuartz.core.common.exception.KzException;
import com.kuartz.core.common.util.KzUtil;
import com.kuartz.core.rest.model.KuartzResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class KuartzExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public KuartzResponse<ExceptionMessage> handleException(Exception e, Locale locale) {
        if (e instanceof KzException) {
            ExceptionMessage exceptionDetail = ((KzException) e).getExceptionDetail();
            String message = messageSource.getMessage(exceptionDetail.getMessage(), exceptionDetail.getArgument(), locale);
            if (!KzUtil.isEmpty(message)) {
                exceptionDetail.setMessage(message);
            }
            return new KuartzResponse<>(((KzException) e).getExceptionDetail(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new KuartzResponse<>(new ExceptionMessage(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}