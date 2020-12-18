package com.kuartz.core.common.exception;

import java.util.Date;

/**
 * @author Kutay Celebi
 * @since 14.12.2020 20:07
 */
public class CodedExceptionMessage extends ExceptionMessage{
    private final String code;

    public CodedExceptionMessage(String message, Object[] messageArgument, String code) {
        super(message, messageArgument);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
