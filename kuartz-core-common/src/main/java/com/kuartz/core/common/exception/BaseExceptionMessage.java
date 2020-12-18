package com.kuartz.core.common.exception;

import com.kuartz.core.common.util.KzDateUtil;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author Kutay Celebi
 * @since 14.12.2020 20:05
 */
public class BaseExceptionMessage {

    /**
     * Exceptionun tarihidir.
     */
    private final Date date = KzDateUtil.now();
    /**
     * Exceptionun kolay trace edilmesi amaciyla exception kaynagini belirtir.
     */
    private Class source;
    /**
     * Exceptionun kolay trace edilmesi amaciyla her bir exceptiona tekil bir numara verilir.
     */
    private final String uuid = UUID.randomUUID().toString();

    private Map<String, Object> exceptionArguments;

    public BaseExceptionMessage() {
    }

    public BaseExceptionMessage(Map<String, Object> exceptionArguments) {
        this.exceptionArguments = exceptionArguments;
    }

    public BaseExceptionMessage(Class source) {
        this.source = source;
    }

    public BaseExceptionMessage(Class source, Map<String, Object> exceptionArguments) {
        this.source             = source;
        this.exceptionArguments = exceptionArguments;
    }

    public void addArgument(String key, Object argument) {
        this.exceptionArguments.put(key, argument);
    }

    public void deleteArgument(String key) {
        this.exceptionArguments.remove(key);
    }

    public Date getDate() {
        return date;
    }

    public Class getSource() {
        return source;
    }

    public String getUuid() {
        return uuid;
    }

    public Map<String, Object> getExceptionArguments() {
        return exceptionArguments;
    }
}
