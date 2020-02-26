package com.kuartz.core.env.configuration;

import com.kuartz.core.env.KuartzMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@ComponentScan(basePackages = "com.kuartz.core.env")
@Configuration
public class KuartzEnvironmentConfiguration {

    @Primary
    @Bean
    public KuartzMessageSource messageSource() {
        return new KuartzMessageSource();
    }
}
