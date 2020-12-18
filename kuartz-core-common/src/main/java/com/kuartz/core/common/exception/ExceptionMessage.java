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
public class ExceptionMessage extends BaseExceptionMessage implements Serializable {
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

    public ExceptionMessage(String message, Object[] messageArgument) {
        this.message         = message;
        this.messageArgument = messageArgument;
    }

    public ExceptionMessage(String message) {
        this.message         = message;
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
}
