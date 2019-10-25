package com.kuartz.core.rest.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class KuartzResponse<T> extends ResponseEntity<T> {

    public KuartzResponse(T body){
        super(body,HttpStatus.OK);
    }

}
