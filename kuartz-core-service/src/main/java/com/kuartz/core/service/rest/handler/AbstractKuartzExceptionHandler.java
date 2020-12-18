package com.kuartz.core.service.rest.handler;

import com.kuartz.core.common.exception.BaseExceptionMessage;
import com.kuartz.core.common.exception.CodedExceptionMessage;
import com.kuartz.core.common.exception.ExceptionMessage;
import com.kuartz.core.common.exception.StackedExceptionMessage;
import com.kuartz.core.common.model.KuartzResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

public abstract class AbstractKuartzExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractKuartzExceptionHandler.class);

    protected abstract <M extends BaseExceptionMessage> M extractException(Exception e, Locale locale);

    @ExceptionHandler(Exception.class)
    public <M extends BaseExceptionMessage> KuartzResponse<M> handleException(Exception e, Locale locale) {
        M exceptionMessage = extractException(e, locale);

        if (exceptionMessage == null) {
            LOGGER.error("Hata : ",e);
            return extractDefaultException(e);
        }

        LOGGER.error("{} : exception cause", exceptionMessage.getUuid());
        LOGGER.error("Exception : ", e);
        return new KuartzResponse<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public <M extends BaseExceptionMessage> KuartzResponse<M> handleRuntimeException(RuntimeException e, Locale locale) {
        M exceptionMessage = extractException(e, locale);
        if (exceptionMessage == null) {
            LOGGER.error("Hata : ",e);
            return extractDefaultException(e);
        }
        LOGGER.error("{} : exception cause", exceptionMessage.getUuid());
        LOGGER.error("Exception : ", e);
        return new KuartzResponse<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private <M extends BaseExceptionMessage> KuartzResponse<M> extractDefaultException(Exception e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(e.getLocalizedMessage() != null ? e.getLocalizedMessage() : "Internal Server Error");
        return new KuartzResponse<>((M) exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}