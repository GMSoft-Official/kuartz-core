package com.kuartz.core.auth.token;

import com.kuartz.core.common.security.KuartzPrincipal;



import com.kuartz.core.common.converter.KuartzModelConverter;
import com.kuartz.core.common.security.KuartzPrincipalModel;
import com.kuartz.core.common.security.KuartzPrincipalRol;
import com.kuartz.core.common.security.KuartzPrincipalYetki;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kutay Celebi
 * @since 11.12.2020 17:17
 */
public abstract class AbstractKuartzUserAuthenticationConverter extends DefaultUserAuthenticationConverter implements
        KuartzUserAuthenticationConverter {

    private final String PRINCIPAL_KEY = "account";

    protected abstract Map<String, String> additionalInfo(KuartzPrincipal authenticatedPrincipal);

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();

        KuartzPrincipal principal = (KuartzPrincipal) authentication.getPrincipal();

        final KuartzPrincipalModel kuartzPrincipalModel = new KuartzPrincipalModel();
        kuartzPrincipalModel.setKullaniciAdi(principal.getKullaniciAdi());

        if (CollectionUtils.isNotEmpty(principal.getRolList())) {
            Set<KuartzPrincipalRol> rolList;
            rolList = principal.getRolList().stream().map(rol -> new KuartzPrincipalRol(rol.getKod())).collect(Collectors.toSet());
            kuartzPrincipalModel.setRolList(rolList);
        }

        if (CollectionUtils.isNotEmpty(principal.getRolList())) {
            Set<KuartzPrincipalYetki> yetkiList;
            yetkiList = principal.getYetkiList().stream().map(rol -> new KuartzPrincipalYetki(rol.getKod())).collect(Collectors.toSet());
            kuartzPrincipalModel.setYetkiList(yetkiList);
        }
        kuartzPrincipalModel.setAdditionalInfo(additionalInfo(principal));

        response.put(PRINCIPAL_KEY, kuartzPrincipalModel);

        if (authentication.getAuthorities() != null && ! authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(PRINCIPAL_KEY)) {
            final KuartzPrincipalModel nekaPrincipalModel = (KuartzPrincipalModel) KuartzModelConverter.getMapper()
                    .convertValue(map.get(PRINCIPAL_KEY), KuartzPrincipalModel.class);

            Collection<? extends GrantedAuthority> authorities = getAuthority(map);

            return new UsernamePasswordAuthenticationToken(nekaPrincipalModel, "N/A", authorities);
        }
        return null;

    }

    private Collection<? extends GrantedAuthority> getAuthority(Map<String, ?> map) {
        //if (! map.containsKey(AUTHORITIES)) {
        //    return defaultAuthorities;
        //}
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(
                    StringUtils.collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }

}
