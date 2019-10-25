package com.kuartz.core.cloud;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@EnableEurekaClient
@Configuration
@EnableAutoConfiguration
public class KuartzCloudConfiguration {
}
