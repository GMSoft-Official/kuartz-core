package com.kuartz.core.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kutay Çelebi
 * @since 28.06.2020
 */
public abstract class AbstractKuartzController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected MessageSource messageSource;

}
