package com.kuartz.core.data.jpa.util;

import com.kuartz.core.common.enumaration.KzEnum;

import javax.persistence.AttributeConverter;

public abstract class KuartzEnumConverter<T extends KzEnum<Integer>> implements AttributeConverter<T, Integer> {

    @Override
    public Integer convertToDatabaseColumn(T attribute) {
        if (attribute == null){
            return null;
        }
        return attribute.code();
    }
}
