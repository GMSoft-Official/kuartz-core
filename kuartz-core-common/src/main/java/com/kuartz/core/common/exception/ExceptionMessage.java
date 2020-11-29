package com.kuartz.core.common.exception;

import com.kuartz.core.common.util.KzDateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Servislerden donulecek exception mesajidir.
 */
public class ExceptionMessage implements Serializable {
    private static final long serialVersionUID = -6447606835316666789L;

    /**
     * Exception mesaji. Eger exceptionun bir bundle keyi ise {@link org.springframework.context.MessageSource} tarafindan yakalanir ve
     * anlamli bir mesaj haline cevrilir
     */
    private String   message;
    /**
     * {@link org.springframework.context.MessageSource} tarafindan cevrilecek mesaj argumanlaridir.
     */
    private Object[] messageArgument;
    /**
     * Exceptionun tarihidir.
     */
    private Date     date = KzDateUtil.now();
    /**
     * Exceptionun kolay trace edilmesi amaciyla exception kaynagini belirtir.
     */
    private Class    source;
    /**
     * Exceptionun kolay trace edilmesi amaciyla her bir exceptiona tekil bir numara verilir.
     */
    private String   uuid = UUID.randomUUID().toString();

    private Map<String, Object> exceptionArguments;

    private String code;


    public ExceptionMessage(String message) {
        this.message = message;
        this.exceptionArguments = new HashMap<>();
    }

    public ExceptionMessage(String message, Class source) {
        this.message = message;
        this.source  = source;
        this.exceptionArguments = new HashMap<>();
    }

    public ExceptionMessage(String message, Date date) {
        this.message = message;
        this.date    = date;
        this.exceptionArguments = new HashMap<>();
    }

    public ExceptionMessage(String message, Object[] messageArgument) {
        this.message         = message;
        this.messageArgument = messageArgument;
        this.exceptionArguments = new HashMap<>();
    }

    public ExceptionMessage(String message, Object[] messageArgument, Class source) {
        this.message         = message;
        this.messageArgument = messageArgument;
        this.source          = source;
        this.exceptionArguments = new HashMap<>();
    }

    public ExceptionMessage(String message, Object[] messageArgument, Date date) {
        this.message         = message;
        this.messageArgument = messageArgument;
        this.date            = date;
        this.exceptionArguments = new HashMap<>();
    }

    public ExceptionMessage(String message, Object[] messageArgument, Date date, Class source) {
        this.message         = message;
        this.messageArgument = messageArgument;
        this.date            = date;
        this.source          = source;
        this.exceptionArguments = new HashMap<>();
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getMessageArgument() {
        return messageArgument;
    }

    public void setMessageArgument(Object[] messageArgument) {
        this.messageArgument = messageArgument;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Class getSource() {
        return source;
    }

    public void setSource(Class source) {
        this.source = source;
    }

    public String getUuid() {
        return uuid;
    }


    public void addArgument(String key, Object argument) {
        this.exceptionArguments.put(key, argument);
    }

    public void deleteArgument(String key) {
        this.exceptionArguments.remove(key);
    }

    public Map<String, Object> getExceptionArguments() {
        return exceptionArguments;
    }

    public String getCode() {
        return code;
    }

    protected void setCode(String code) {
        this.code = code;
    }
}
