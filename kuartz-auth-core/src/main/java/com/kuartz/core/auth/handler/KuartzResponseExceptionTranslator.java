package com.kuartz.core.auth.handler;

import com.kuartz.core.common.exception.ExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class KuartzResponseExceptionTranslator implements WebResponseExceptionTranslator {

    public static final Logger LOG = LoggerFactory.getLogger(KuartzResponseExceptionTranslator.class);

    @Override
    public ResponseEntity<ExceptionMessage> translate(Exception e) throws Exception {
        LOG.debug(e.getMessage(), e);
        return new ResponseEntity<>(new ExceptionMessage(e.getMessage()),
                                    Objects.requireNonNull(HttpStatus.resolve(((OAuth2Exception) e).getHttpErrorCode())));
    }
}
