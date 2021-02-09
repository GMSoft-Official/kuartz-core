package com.kuartz.core.common.model;

/**
 * @author Kutay Celebi
 * @since 4.02.2021 01:34
 */
public class KuartzResponseModel<T> {
    private boolean success = true;
    private T body;

    public KuartzResponseModel() {
    }

    public KuartzResponseModel(boolean success, T body) {
        this.success = success;
        this.body    = body;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
