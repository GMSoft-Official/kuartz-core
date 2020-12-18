package com.kuartz.core.data.jpa.bean;

import com.kuartz.core.common.security.KuartzPrincipalModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Kutay Celebi
 * @since 17.12.2020 11:41
 */
public class KuartzAuditorAware implements AuditorAware<String> {
    private static final Logger LOG = LoggerFactory.getLogger(KuartzAuditorAware.class);

    @Override
    public Optional<String> getCurrentAuditor() {
        final SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return Optional.empty();
        }

        final Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }

        final Object principal = authentication.getPrincipal();
        if (principal instanceof KuartzPrincipalModel) {
            final String kullaniciAdi = ((KuartzPrincipalModel) principal).getKullaniciAdi();

            if (kullaniciAdi == null) {
                LOG.warn("Kayıt yapılırken kullanıcı bilgisi alınamadı...");
                return Optional.empty();
            }

            return Optional.of(kullaniciAdi);
        }

        if (principal instanceof String) {
            LOG.debug("Anonymous user ile kayıt yapıldı.");
            return Optional.empty();
        }


        return Optional.empty();
    }
}
