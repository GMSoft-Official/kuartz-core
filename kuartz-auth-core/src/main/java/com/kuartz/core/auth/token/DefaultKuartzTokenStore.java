package com.kuartz.core.auth.token;

/**
 * @author Kutay Celebi
 * @since 11.12.2020 18:04
 */
public class DefaultKuartzTokenStore extends AbstractKuartzTokenStore{
    public DefaultKuartzTokenStore(KuartzJwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }
}
