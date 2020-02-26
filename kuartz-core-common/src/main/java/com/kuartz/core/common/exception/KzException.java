package com.kuartz.core.common.exception;

public class KzException extends Exception implements KzBaseException {
    private static final long serialVersionUID = 8916428016458346879L;

    private ExceptionMessage exceptionMessage;

    public KzException() {
        // bos yapici.
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
