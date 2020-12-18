package com.kuartz.core.common.exception;

public class KzException extends RuntimeException implements KzBaseException<ExceptionMessage> {

    private final ExceptionMessage exceptionMessage;

    public KzException(String message, Throwable cause) {
        super(message, cause);
        this.exceptionMessage = new ExceptionMessage(message, null);
    }

    public KzException(String message) {
        super(message);
        this.exceptionMessage = new ExceptionMessage(message, null);
    }

    public KzException(String message, Object[] messageArgument) {
        super(message);
        this.exceptionMessage = new ExceptionMessage(message, messageArgument);
    }


    @Override
    public ExceptionMessage getExceptionDetail() {
        return exceptionMessage;
    }
}
