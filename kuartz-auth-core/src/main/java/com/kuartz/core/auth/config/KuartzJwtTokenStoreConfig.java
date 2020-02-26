package com.kuartz.core.auth.config;

import com.kuartz.core.auth.config.property.KuartzSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration("kuartzJwtTokenStoreConfig")
@EnableConfigurationProperties({KuartzSecurityProperties.class})
public class KuartzJwtTokenStoreConfig {

    @Autowired
    private KuartzSecurityProperties kuartzSecurityProperties;


    @Bean
    @Primary
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(accesTokenConverter());
    }

    @Bean
    @Primary
    public JwtAccessTokenConverter accesTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(kuartzSecurityProperties.getJwtSignKey());
        return converter;
    }
}
