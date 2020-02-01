package com.kuartz.core.common.exception;


import java.util.Date;

public class CoddedExceptionMessage extends ExceptionMessage {
    private static final long serialVersionUID = -7653215542894001525L;

    private String code;

    public CoddedExceptionMessage(String message, String code) {
        super(message);
        this.code = code;
    }

    public CoddedExceptionMessage(String message, Class source, String code) {
        super(message, source);
        this.code = code;
    }

    public CoddedExceptionMessage(String message, Date date, String code) {
        super(message, date);
        this.code = code;
    }

    public CoddedExceptionMessage(String message, Object[] messageArgument, String code) {
        super(message, messageArgument);
        this.code = code;
    }

    public CoddedExceptionMessage(String message, Object[] messageArgument, Class source, String code) {
        super(message, messageArgument, source);
        this.code = code;
    }

    public CoddedExceptionMessage(String message, Object[] messageArgument, Date date, String code) {
        super(message, messageArgument, date);
        this.code = code;
    }

    public CoddedExceptionMessage(String message, Object[] messageArgument, Date date, Class source, String code) {
        super(message, messageArgument, date, source);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
