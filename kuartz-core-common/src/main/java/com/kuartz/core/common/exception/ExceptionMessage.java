package com.kuartz.core.common.exception;

import com.kuartz.core.common.util.DateUtils;

import java.io.Serializable;
import java.util.Date;
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
    private Date     date = DateUtils.suankiTarih();
    /**
     * Exceptionun kolay trace edilmesi amaciyla exception kaynagini belirtir.
     */
    private Class    source;
    /**
     * Exceptionun kolay trace edilmesi amaciyla her bir exceptiona tekil bir numara verilir.
     */
    private String   uuid = UUID.randomUUID().toString();

    public ExceptionMessage(String message) {
        this.message = message;
    }

    public ExceptionMessage(String message, Class source) {
        this.message = message;
        this.source  = source;
    }

    public ExceptionMessage(String message, Date date) {
        this.message = message;
        this.date    = date;
    }

    public ExceptionMessage(String message, Object[] messageArgument) {
        this.message         = message;
        this.messageArgument = messageArgument;
    }

    public ExceptionMessage(String message, Object[] messageArgument, Class source) {
        this.message         = message;
        this.messageArgument = messageArgument;
        this.source          = source;
    }

    public ExceptionMessage(String message, Object[] messageArgument, Date date) {
        this.message         = message;
        this.messageArgument = messageArgument;
        this.date            = date;
    }

    public ExceptionMessage(String message, Object[] messageArgument, Date date, Class source) {
        this.message         = message;
        this.messageArgument = messageArgument;
        this.date            = date;
        this.source          = source;
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
}
