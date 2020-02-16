package com.kuartz.core.rest.handler;

import com.kuartz.core.common.exception.CoddedExceptionMessage;
import com.kuartz.core.common.exception.ExceptionMessage;
import com.kuartz.core.common.exception.KzCoddedException;
import com.kuartz.core.common.exception.KzException;
import com.kuartz.core.common.util.KzUtil;
import com.kuartz.core.rest.model.KuartzResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class KuartzExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(KuartzExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public KuartzResponse<ExceptionMessage> handleException(Exception e, Locale locale) throws Exception {
        if (e instanceof KzException) {
            KzException kzException = (KzException) e;
            ExceptionMessage exceptionDetail = kzException.getExceptionDetail();
            return extractExceptionMessage(locale, exceptionDetail);
        } else if (e instanceof KzCoddedException){
            KzCoddedException kzException = (KzCoddedException) e;
            CoddedExceptionMessage exceptionDetail = kzException.getExceptionDetail();
            return extractExceptionMessage(locale, exceptionDetail);

        }
        ExceptionMessage exceptionMessage = new ExceptionMessage(e.getLocalizedMessage());
        LOGGER.error("{} : exception cause {}", exceptionMessage.getUuid(), exceptionMessage.getMessage());
        LOGGER.error("Exception : ",e);
        return new KuartzResponse<>(exceptionMessage);
    }

    private KuartzResponse<ExceptionMessage> extractExceptionMessage(Locale locale, ExceptionMessage exceptionDetail) {
        String message = messageSource.getMessage(exceptionDetail.getMessage(), exceptionDetail.getMessageArgument(), locale);
        if (!KzUtil.isEmpty(message)) {
            exceptionDetail.setMessage(message);
        }
        LOGGER.error("{} : exception on {} cause {}", exceptionDetail.getUuid(), exceptionDetail.getSource(),
                     exceptionDetail.getMessage());
        return new KuartzResponse<>(exceptionDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}