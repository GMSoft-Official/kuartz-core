package com.kuartz.core.service.config;

import com.kuartz.core.env.KuartzMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author Kutay Çelebi
 * @since 28.06.2020
 */
@Configuration
public class KuartzServiceConfig {
    @Autowired
    private KuartzMessageSource messageSource;

    @PostConstruct
    public void kuartzMessageSource() {
        messageSource.addBasenames("base_service_messages");
    }
}
