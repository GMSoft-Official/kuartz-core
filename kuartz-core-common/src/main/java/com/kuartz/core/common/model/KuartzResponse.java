package com.kuartz.core.common.model;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class KuartzResponse<T> extends ResponseEntity<KuartzResponseModel<T>> {

    public KuartzResponse() {
        super(HttpStatus.OK);
    }

    public KuartzResponse(T body) {
        super(KuartzResponseModel.<T>builder().data(body).success(Boolean.TRUE).build(),HttpStatus.OK);
    }

    public KuartzResponse(T body, boolean success) {
        super(new KuartzResponseModel<>(success, body), HttpStatus.OK);
    }

    public KuartzResponse(HttpStatus status) {
        super(status);
    }

    public KuartzResponse(T body, HttpStatus status, boolean success) {
        super(new KuartzResponseModel<>(success, body), status);
    }

    public KuartzResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

}
