package com.kuartz.env.configuration;

import com.kuartz.env.factory.KuartzMessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@ComponentScan(basePackages = "com.kuartz.env")
@Configuration
public class KuartzEnvironmentConfiguration {

    @Autowired
    private KuartzMessageFactory messageFactory;

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        if (messageFactory.getBundleDir() != null) {
            messageFactory.getBundleDir().forEach(messageSource::setBasenames);
        }
        return messageSource;
    }
}
