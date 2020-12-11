package com.kuartz.core.auth.token;

import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Kutay Celebi
 * @since 11.12.2020 18:00
 */
public abstract class AbstractKuartzTokenStore extends JwtTokenStore {

    public AbstractKuartzTokenStore(KuartzJwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }
}
