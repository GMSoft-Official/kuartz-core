package com.kuartz.core.service.rest.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class KuartzResponse<T> extends ResponseEntity<T> {

    public KuartzResponse(T body){
        super(body,HttpStatus.OK);
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
}
