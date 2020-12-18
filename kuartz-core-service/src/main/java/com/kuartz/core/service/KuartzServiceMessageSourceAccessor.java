package com.kuartz.core.service;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.SpringSecurityMessageSource;

/**
 * @author Kutay Celebi
 * @since 18.12.2020 23:06
 */
public class KuartzServiceMessageSourceAccessor extends ResourceBundleMessageSource {

    public KuartzServiceMessageSourceAccessor() {
        setDefaultEncoding("UTF-8");
        setBasename("service_message");
    }

    public static MessageSourceAccessor getAccessor() {
        return new MessageSourceAccessor(new KuartzServiceMessageSourceAccessor());
    }
}
