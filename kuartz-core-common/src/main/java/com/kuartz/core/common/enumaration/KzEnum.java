package com.kuartz.core.common.enumaration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base enum interface to be used when stored in database
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface KzEnum {
    @JsonProperty("label")
    String label();
    @JsonProperty("code")
    Integer code();
}
