package com.kuartz.core.service.rest.handler;

import com.kuartz.core.common.exception.BaseExceptionMessage;
import com.kuartz.core.common.exception.ExceptionMessage;
import com.kuartz.core.common.model.KuartzResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

public abstract class AbstractKuartzExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractKuartzExceptionHandler.class);

    protected abstract <M extends BaseExceptionMessage> KuartzResponse<M> extractException(Exception e, Locale locale);

    @ExceptionHandler(Exception.class)
    public <M extends BaseExceptionMessage> KuartzResponse<M> handleException(Exception e, Locale locale) {
        KuartzResponse<M> exceptionMessage = extractException(e, locale);

        if (exceptionMessage == null) {
            return extractDefaultException(e);
        }

        LOGGER.error("UUID : {} : exception cause", exceptionMessage.getBody().getData().getUuid());
        LOGGER.error("Exception : ", e);
        return exceptionMessage;
    }

    @ExceptionHandler(RuntimeException.class)
    public <M extends BaseExceptionMessage> KuartzResponse<M> handleRuntimeException(RuntimeException e, Locale locale) {
        KuartzResponse<M> exceptionMessage = extractException(e, locale);
        if (exceptionMessage == null) {
            return extractDefaultException(e);
        }
        LOGGER.error("UUID : {} : exception cause", exceptionMessage.getBody().getData().getUuid());
        LOGGER.error("Exception : ", e);
        return exceptionMessage;
    }

    private <M extends BaseExceptionMessage> KuartzResponse<M> extractDefaultException(Exception e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(e.getLocalizedMessage() != null ? e.getLocalizedMessage() : "Internal Server Error");
        return new KuartzResponse<>((M) exceptionMessage, false);
    }
}
