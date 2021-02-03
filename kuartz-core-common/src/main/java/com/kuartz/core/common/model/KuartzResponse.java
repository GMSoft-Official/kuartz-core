package com.kuartz.core.common.model;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class KuartzResponse<T> extends ResponseEntity<T> {
    @Builder.Default
    private boolean success = true;

    public KuartzResponse(T body) {
        super(body, HttpStatus.OK);
        this.success = true;
    }

    public KuartzResponse(T body,boolean success) {
        super(body, HttpStatus.OK);
        this.success = success;
    }

    public KuartzResponse(HttpStatus status) {
        super(status);
    }

    public KuartzResponse(T body, HttpStatus status) {
        super(body, status);
    }

    public KuartzResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public KuartzResponse(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public KuartzResponse(HttpStatus status, boolean success) {
        super(status);
        this.success = success;
    }

    public KuartzResponse(T body, HttpStatus status, boolean success) {
        super(body, status);
        this.success = success;
    }

    public KuartzResponse(MultiValueMap<String, String> headers, HttpStatus status, boolean success) {
        super(headers, status);
        this.success = success;
    }

    public KuartzResponse(T body, MultiValueMap<String, String> headers, HttpStatus status, boolean success) {
        super(body, headers, status);
        this.success = success;
    }
}
