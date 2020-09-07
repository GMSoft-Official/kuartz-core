package com.kuartz.core.common.exception;

public class KzCheckedException extends Exception implements KzBaseException {
    private static final long serialVersionUID = 8916428016458346879L;

    private ExceptionMessage exceptionMessage;

    public KzCheckedException() {
        // bos yapici.
    }

    public KzCheckedException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public KzCheckedException(String s, ExceptionMessage exceptionMessage) {
        super(s);
        this.exceptionMessage = exceptionMessage;
    }

    public KzCheckedException(String s, Throwable throwable, ExceptionMessage exceptionMessage) {
        super(s, throwable);
        this.exceptionMessage = exceptionMessage;
    }

    public KzCheckedException(Throwable throwable, ExceptionMessage exceptionMessage) {
        super(throwable);
        this.exceptionMessage = exceptionMessage;
    }


    @Override
    public ExceptionMessage getExceptionDetail() {
        return exceptionMessage;
    }
}
