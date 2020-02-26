package com.kuartz.core.auth.config;

import com.kuartz.core.auth.security.KuartzMethodSecurityExpressionHandler;
import com.kuartz.core.auth.security.KuartzPermissionEvulator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        final KuartzMethodSecurityExpressionHandler handler = new KuartzMethodSecurityExpressionHandler();
        handler.setPermissionEvaluator(new KuartzPermissionEvulator());
        return handler;
    }
}
