package com.kuartz.core.auth.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class KuartzMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler
        implements MethodSecurityExpressionHandler {

    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        final KuartzSecurityExpressionRoot root = new KuartzSecurityExpressionRoot(authentication);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}
