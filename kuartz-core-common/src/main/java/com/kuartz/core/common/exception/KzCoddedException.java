package com.kuartz.core.common.exception;

import com.kuartz.core.common.enumaration.ExceptionCode;

import java.util.Date;

public class KzCoddedException extends Exception implements KzBaseException {

    private static final long serialVersionUID = -5042458415613778685L;

    private CoddedExceptionMessage exceptionMessage;

    public KzCoddedException(String message, ExceptionCode code) {
        super(message);
        this.exceptionMessage = new CoddedExceptionMessage(message, code.exceptionCode());
    }

    public KzCoddedException(String message, Class source, ExceptionCode code) {
        super(message);
        this.exceptionMessage = new CoddedExceptionMessage(message, source, code.exceptionCode());
    }

    public KzCoddedException(String message, Date date, ExceptionCode code) {
        super(message);
        this.exceptionMessage = new CoddedExceptionMessage(message, date, code.exceptionCode());
    }

    public KzCoddedException(String message, Object[] argument, ExceptionCode code) {
        super(message);
        this.exceptionMessage = new CoddedExceptionMessage(message, argument, code.exceptionCode());
    }

    public KzCoddedException(String message, Object[] argument, Class source, ExceptionCode code) {
        super(message);
        this.exceptionMessage = new CoddedExceptionMessage(message, argument, source, code.exceptionCode());
    }

    public KzCoddedException(String message, Object[] argument, Date date, ExceptionCode code) {
        super(message);
        this.exceptionMessage = new CoddedExceptionMessage(message, argument, date, code.exceptionCode());
    }

    public KzCoddedException(String message, Object[] argument, Date date, Class source, ExceptionCode code) {
        super(message);
        this.exceptionMessage = new CoddedExceptionMessage(message, argument, date, source, code.exceptionCode());
    }

    @Override
    public CoddedExceptionMessage getExceptionDetail() {
        return exceptionMessage;
    }

}
