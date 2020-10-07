package com.kuartz.core.common.exception;

public class KzException extends RuntimeException implements KzBaseException {

    private ExceptionMessage exceptionMessage;

    public KzException() {
        super();
    }

    public KzException(String message) {
        this.exceptionMessage = new ExceptionMessage(message);
        this.exceptionMessage.setMessage(message);
    }

    public KzException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public KzException(String s, ExceptionMessage exceptionMessage) {
        super(s);
        this.exceptionMessage = exceptionMessage;
    }

    public KzException(String s, Throwable throwable, ExceptionMessage exceptionMessage) {
        super(s, throwable);
        this.exceptionMessage = exceptionMessage;
    }

    public KzException(Throwable throwable, ExceptionMessage exceptionMessage) {
        super(throwable);
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public ExceptionMessage getExceptionDetail() {
        return exceptionMessage;
    }
}
