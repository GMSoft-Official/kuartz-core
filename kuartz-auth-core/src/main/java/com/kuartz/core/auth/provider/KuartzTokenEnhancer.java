package com.kuartz.core.auth.provider;

import com.kuartz.core.auth.dto.UserModel;
import com.kuartz.core.auth.dto.principal.KuartzAuthority;
import com.kuartz.core.auth.dto.principal.KuartzPrincipalModel;
import com.kuartz.core.auth.dto.principal.KuartzRole;
import com.kuartz.core.common.converter.KuartzModelConverter;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KuartzTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultToken = new DefaultOAuth2AccessToken(accessToken);
        if (authentication.isAuthenticated()) {
            UserModel user = KuartzModelConverter.convert(authentication.getPrincipal(), UserModel.class);
            List<KuartzRole> roles = user.getRoleList().stream().map(r -> new KuartzRole(r.getCode())).collect(Collectors.toList());
            List<KuartzAuthority> privileges = user.getAuthorities().stream().map(p -> new KuartzAuthority(p.getAuthority())).collect(
                    Collectors.toList());


            KuartzPrincipalModel principalModel = new KuartzPrincipalModel();
            principalModel.setUsername(user.getUsername());
            principalModel.setEmail(user.getEmail());
            principalModel.setAuthority(privileges);
            principalModel.setRoles(roles);

            defaultToken.getAdditionalInformation().put("principal", principalModel);
        }
        return defaultToken;
    }


}
