package com.kuartz.core.common.exception;

import com.kuartz.core.common.enumaration.ExceptionCode;

// todo @kcelebi propertiesden ayarlanabilir exception code prefix ekleyelim.
public class KzCoddedException extends Exception implements KzBaseException<CodedExceptionMessage> {

    private static final long serialVersionUID = - 5042458415613778685L;

    private final CodedExceptionMessage exceptionMessage;

    public KzCoddedException(ExceptionCode code) {
        this.exceptionMessage = new CodedExceptionMessage(code.label(),null, code.exceptionCode());
    }

    public KzCoddedException(ExceptionCode code, Object[] messageArgument) {
        this.exceptionMessage = new CodedExceptionMessage(code.label(), messageArgument, code.exceptionCode());
    }

    @Override
    public CodedExceptionMessage getExceptionDetail() {
        return exceptionMessage;
    }
}
