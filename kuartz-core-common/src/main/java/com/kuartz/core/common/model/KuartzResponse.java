package com.kuartz.core.common.model;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class KuartzResponse<T> extends ResponseEntity<KuartzResponseModel<T>> {

    public KuartzResponse(T body) {
        super(new KuartzResponseModel<>(true,body), HttpStatus.OK);
    }

    public KuartzResponse(T body, boolean success) {
        super(new KuartzResponseModel<>(success,body), HttpStatus.OK);
    }

    public KuartzResponse(HttpStatus status) {
        super(status);
    }

    public KuartzResponse(T body,HttpStatus status,boolean success) {
        super(new KuartzResponseModel<>(success,body), status);
    }

    public KuartzResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }


}
