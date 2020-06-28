package com.kuartz.core.auth.handler;

import com.kuartz.core.auth.KzAuthenticationException;
import com.kuartz.core.env.KuartzMessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class KuartzAuthenticationEntryPoint extends AbstractOAuth2SecurityExceptionHandler implements AuthenticationEntryPoint {

    public static final Logger LOG = LoggerFactory.getLogger(KuartzAuthenticationEntryPoint.class);

    @Autowired
    private KuartzMessageSource messageSource;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws
            IOException, ServletException {
        LOG.error(e.getMessage(), e);
        String message = messageSource.getMessage("ex_duringAuth", null, httpServletRequest.getLocale());
        doHandle(httpServletRequest, httpServletResponse, new KzAuthenticationException(message));
    }
}
