package com.kuartz.core.auth.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Kutay Celebi
 * @since 11.12.2020 17:14
 */
public abstract class AbstractKuartzAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider implements
        KuartzUserAuthenticationConverter {

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
