package com.kuartz.core.feign.configuration;

import com.kuartz.core.feign.property.KuartzApiProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KuartzApiProperty.class)
public class KuartzFeignConfiguration {
}
