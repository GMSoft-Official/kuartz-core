package com.kuartz.core.common.model;

import com.kuartz.core.common.util.KzUtil;

import java.util.ArrayList;
import java.util.List;

public class KzMessageModel {
    private String message;

    private Boolean success;

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
        if (!KzUtil.isEmpty(argumentList)) {
            argumentList = new ArrayList<>();
        }
        return this;
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
}
