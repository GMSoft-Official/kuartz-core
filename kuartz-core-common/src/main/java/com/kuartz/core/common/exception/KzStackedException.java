package com.kuartz.core.common.exception;

/**
 * @author Kutay Celebi
 * @since 14.12.2020 20:04
 */
public class KzStackedException extends RuntimeException implements KzBaseException<StackedExceptionMessage>{

    private final StackedExceptionMessage stackedMessage;

    public KzStackedException(StackedExceptionMessage stackedMessage) {
        this.stackedMessage = stackedMessage;
    }

    @Override
    public StackedExceptionMessage getExceptionDetail() {
        return stackedMessage;
    }


}
