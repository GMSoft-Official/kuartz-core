package com.kuartz.core.common.model;

import com.kuartz.core.common.util.KzUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class KzMessageModel {
    private String       message;
    private List<String> warnings;
    private Boolean      success;
    private Boolean      warning;
    private List<Object> argumentList;

    public static KzMessageModel succeed() {
        KzMessageModel obj = new KzMessageModel();
        obj.setSuccess(true);
        return obj;
    }

    public static KzMessageModel fail() {
        KzMessageModel obj = new KzMessageModel();
        obj.setSuccess(false);
        return obj;
    }

    public KzMessageModel addMessage(String message) {
        this.message = message;
        return this;
    }

    public KzMessageModel addArgument(Object o) {
        if (CollectionUtils.isEmpty(argumentList)) {
            argumentList = new ArrayList<>();
        }
        return this;
    }

    public KzMessageModel addWarnings(String warning) {
        this.warning = true;
        if (CollectionUtils.isEmpty(warnings)) {
            this.warnings = new ArrayList<>();
        }
        this.warnings.add(warning);
        return this;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Object> getArgumentList() {
        return argumentList;
    }

    public void setArgumentList(List<Object> argumentList) {
        this.argumentList = argumentList;
    }

    public Boolean getWarning() {
        return warning;
    }
}
