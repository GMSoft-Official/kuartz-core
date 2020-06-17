package com.kuartz.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public abstract class KuartzService {

    @Autowired
    protected HttpServletRequest request;

}
