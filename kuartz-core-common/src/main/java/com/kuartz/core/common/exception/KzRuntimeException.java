package com.kuartz.core.common.exception;

public class KzRuntimeException extends RuntimeException implements KzBaseException {

    private ExceptionMessage exceptionMessage;

    public KzRuntimeException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public KzRuntimeException() {
        super();
    }


    public KzRuntimeException(String s, ExceptionMessage exceptionMessage) {
        super(s);
        this.exceptionMessage = exceptionMessage;
    }

    public KzRuntimeException(String s, Throwable throwable, ExceptionMessage exceptionMessage) {
        super(s, throwable);
        this.exceptionMessage = exceptionMessage;
    }

    public KzRuntimeException(Throwable throwable, ExceptionMessage exceptionMessage) {
        super(throwable);
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public ExceptionMessage getExceptionDetail() {
        return exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
