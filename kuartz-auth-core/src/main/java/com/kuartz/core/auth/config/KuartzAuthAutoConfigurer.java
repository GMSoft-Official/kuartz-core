//package com.kuartz.core.auth.config;
//
//import com.kuartz.core.auth.token.AbstractKuartzUserAuthenticationConverter;
//import com.kuartz.core.auth.token.DefaultKuartzTokenStore;
//import com.kuartz.core.auth.token.DefaultKuartzUserAuthenticationConverter;
//import com.kuartz.core.auth.token.KuartzAccesTokenConverter;
//import com.kuartz.core.auth.token.KuartzJwtAccessTokenConverter;
//import com.kuartz.core.auth.token.KuartzUserAuthenticationConverter;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
//import org.springframework.boot.autoconfigure.condition.SearchStrategy;
//import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//
///**
// * @author Kutay Celebi
// * @since 11.12.2020 17:34
// */
//@Configuration
//@AutoConfigureAfter(value = OAuth2AuthorizationServerConfiguration.class)
//public class KuartzAuthAutoConfigurer {
//
//    @Bean
//    @ConditionalOnMissingBean
//    public DefaultKuartzTokenStore tokenStore() {
//        return new DefaultKuartzTokenStore(jwtAccesTokenConverter());
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public KuartzJwtAccessTokenConverter jwtAccesTokenConverter() {
//        return new KuartzJwtAccessTokenConverter(accesTokenConverter());
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(KuartzUserAuthenticationConverter.class)
//    public AbstractKuartzUserAuthenticationConverter userAuthenticationConverter() {
//        return new DefaultKuartzUserAuthenticationConverter();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public KuartzAccesTokenConverter accesTokenConverter() {
//        return new KuartzAccesTokenConverter(userAuthenticationConverter());
//    }
//}
