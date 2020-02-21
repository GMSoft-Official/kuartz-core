package com.kuartz.core.env.configuration;

import com.kuartz.core.env.factory.KuartzMessageFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

//@ComponentScan(basePackages = "com.kuartz.core")
@Configuration
public class KuartzEnvironmentConfiguration {

    @Bean
    public KuartzMessageFactory kuartzMessageFactory(){
        return new KuartzMessageFactory();
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        if (kuartzMessageFactory().getBundleDir() != null) {
            kuartzMessageFactory().getBundleDir().forEach(messageSource::setBasenames);
        }
        return messageSource;
    }
}
