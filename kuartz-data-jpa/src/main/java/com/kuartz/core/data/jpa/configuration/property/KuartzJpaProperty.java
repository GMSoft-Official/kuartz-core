package com.kuartz.core.data.jpa.configuration.property;

import com.kuartz.core.data.jpa.initializer.KuartzScriptUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "kuartz.jpa.init")
public class KuartzJpaProperty {
    private List<String> dataSource;

    private Boolean enable;

    private Boolean contiveOnError = Boolean.FALSE;

    private String sqlCommentPrefix = KuartzScriptUtil.DEFAULT_COMMENT_PREFIX;

    private String sqlLineSeperator = KuartzScriptUtil.DEFAULT_STATEMENT_SEPARATOR;

    public List<String> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<String> dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getContiveOnError() {
        return contiveOnError;
    }

    public void setContiveOnError(Boolean contiveOnError) {
        this.contiveOnError = contiveOnError;
    }

    public String getSqlCommentPrefix() {
        return sqlCommentPrefix;
    }

    public void setSqlCommentPrefix(String sqlCommentPrefix) {
        this.sqlCommentPrefix = sqlCommentPrefix;
    }

    public String getSqlLineSeperator() {
        return sqlLineSeperator;
    }

    public void setSqlLineSeperator(String sqlLineSeperator) {
        this.sqlLineSeperator = sqlLineSeperator;
    }
}
