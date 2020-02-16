package com.kuartz.core.common.exception;

import java.util.Date;

public class KzException extends Exception implements KzBaseException {
    private static final long serialVersionUID = 8916428016458346879L;

    private ExceptionMessage exceptionMessage;

    public KzException() {
        // bos yapici.
    }

    public KzException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public KzException(String message) {
        super(message);
        this.exceptionMessage = new ExceptionMessage(message);
    }

    public KzException(String message, Class source) {
        super(message);
        this.exceptionMessage = new ExceptionMessage(message, source);
    }

    public KzException(String message, Date date) {
        super(message);
        this.exceptionMessage = new ExceptionMessage(message, date);
    }

    public KzException(String message, Object[] argument) {
        super(message);
        this.exceptionMessage = new ExceptionMessage(message, argument);
    }

    public KzException(String message, Object[] argument, Class source) {
        super(message);
        this.exceptionMessage = new ExceptionMessage(message, argument, source);
    }

    public KzException(String message, Object[] argument, Date date) {
        super(message);
        this.exceptionMessage = new ExceptionMessage(message, argument, date);
    }

    public KzException(String message, Object[] argument, Date date, Class source) {
        super(message);
        this.exceptionMessage = new ExceptionMessage(message, argument, date, source);
    }

    @Override
    public ExceptionMessage getExceptionDetail() {
        return exceptionMessage;
    }
}
