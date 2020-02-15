package com.kuartz.core.auth.property;

import com.kuartz.core.auth.constants.KuartzSecurityConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kuartz.security")
public class KuartzSecurityProperties {

    private String jwtSignKey = KuartzSecurityConstants.DEFAULT_JWT_SIGN_KEY;

    public String getJwtSignKey() {
        return jwtSignKey;
    }

    public void setJwtSignKey(String jwtSignKey) {
        this.jwtSignKey = jwtSignKey;
    }
}
