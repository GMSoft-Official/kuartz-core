package com.kuartz.core.common.exception;


import java.util.Date;

public class CoddedExceptionMessage extends ExceptionMessage {
    private static final long serialVersionUID = -7653215542894001525L;

    private String code;

    public CoddedExceptionMessage(String message) {
        super(message);
    }

    public CoddedExceptionMessage(String message, Class source) {
        super(message, source);
    }

    public CoddedExceptionMessage(String message, Date date) {
        super(message, date);
    }

    public CoddedExceptionMessage(String message, Object[] messageArgument) {
        super(message, messageArgument);
    }

    public CoddedExceptionMessage(String message, Object[] messageArgument, Class source) {
        super(message, messageArgument, source);
    }

    public CoddedExceptionMessage(String message, Object[] messageArgument, Date date) {
        super(message, messageArgument, date);
    }

    public CoddedExceptionMessage(String message, Object[] messageArgument, Date date, Class source) {
        super(message, messageArgument, date, source);
    }

    public String getCode() {
        return code;
    }

    protected void setCode(String code) {
        this.code = code;
    }
}
