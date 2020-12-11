package com.kuartz.core.auth.token;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author Kutay Celebi
 * @since 11.12.2020 17:16
 */
public class KuartzAccesTokenConverter<AC extends AbstractKuartzUserAuthenticationConverter> extends DefaultAccessTokenConverter {

    public KuartzAccesTokenConverter(AC abstractKuartzUserAuthenticationConverter) {
        this.setUserTokenConverter(abstractKuartzUserAuthenticationConverter);
    }

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        return super.convertAccessToken(token, authentication);
    }

    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
        return super.extractAccessToken(value, map);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        return super.extractAuthentication(map);
    }
}
