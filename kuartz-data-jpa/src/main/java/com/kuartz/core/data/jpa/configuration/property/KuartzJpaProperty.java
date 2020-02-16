package com.kuartz.core.data.jpa.configuration.property;

import com.kuartz.core.data.jpa.initializer.KuartzScriptUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "kuartz.jpa.init")
public class KuartzJpaProperty {
    private List<String> dataSource;

    private Boolean enable;

    private Boolean contiveOnError = Boolean.FALSE;

    private String sqlCommentPrefix = KuartzScriptUtil.DEFAULT_COMMENT_PREFIX;

    private String sqlLineSeperator = KuartzScriptUtil.DEFAULT_STATEMENT_SEPARATOR;
}
