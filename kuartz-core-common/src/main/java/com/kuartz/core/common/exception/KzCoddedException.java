package com.kuartz.core.common.exception;

import com.kuartz.core.common.enumaration.ExceptionCode;

// todo @kcelebi propertiesden ayarlanabilir exception code prefix ekleyelim.
public class KzCoddedException extends Exception implements KzBaseException {

    private static final long serialVersionUID = -5042458415613778685L;

    private ExceptionMessage exceptionMessage;

    private String code;

    public KzCoddedException() {
        // bos yapici
    }

    public KzCoddedException(ExceptionMessage exceptionMessage, ExceptionCode code) {
        this.exceptionMessage = exceptionMessage;
        this.code             = code.exceptionCode();
    }

    public KzCoddedException(String message, ExceptionCode code) {
        super(message);
        this.exceptionMessage = new ExceptionMessage(message);
        this.code             = code.exceptionCode();
    }

    @Override
    public ExceptionMessage getExceptionDetail() {
        return exceptionMessage;
    }

    public String getCode() {
        return code;
    }
}
