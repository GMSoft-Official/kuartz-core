package com.kuartz.core.rest.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Kutay Celebi
 * @since 27.10.2019
 *
 * @param <R> body
 */
public class KuartzResponse<R> extends ResponseEntity<R> {

    public KuartzResponse(R body){
        super(body, HttpStatus.OK);
    }

    public KuartzResponse (HttpStatus status) {
        super(status);
    }

    public KuartzResponse (R body, HttpStatus status) {
        super(body, status);
    }
}
