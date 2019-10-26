package com.kuartz.core.common.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KuartzModelConverter {


    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static <T> T convert (Object from, TypeReference<T> toClass) {
        Object o = objectMapper.convertValue(from, toClass);
        return (T) o;

    }

    public static <T> T convert (Object from, Class<T> to) {
        return objectMapper.convertValue(from, to);
    }
}
