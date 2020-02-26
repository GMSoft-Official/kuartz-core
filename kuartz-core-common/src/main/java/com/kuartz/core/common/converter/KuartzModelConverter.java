package com.kuartz.core.common.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KuartzModelConverter {


    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T convert(Object from, TypeReference<T> toClass) {
        Object o = OBJECT_MAPPER.convertValue(from, toClass);
        return (T) o;

    }

    public static <T> T convert(Object from, Class<T> to) {
        return OBJECT_MAPPER.convertValue(from, to);
    }

    public static ObjectMapper getMapper() {
        return OBJECT_MAPPER;
    }
}
