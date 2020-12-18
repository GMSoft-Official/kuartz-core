package com.kuartz.core.data.jpa.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Kutay Celebi
 * @since 17.12.2020 11:52
 */
@ConfigurationProperties(prefix = "kuartz.jpa.audit")
public class KuartzAuditProperty {
    public Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
