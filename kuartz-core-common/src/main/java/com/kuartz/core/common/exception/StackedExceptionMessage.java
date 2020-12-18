package com.kuartz.core.common.exception;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kutay Celebi
 * @since 14.12.2020 20:05
 */
public class StackedExceptionMessage extends BaseExceptionMessage {

    private List<ExceptionMessage> exceptionStack;

    public StackedExceptionMessage() {
    }

    public StackedExceptionMessage(List<ExceptionMessage> exceptionStack) {
        this.exceptionStack = exceptionStack;
    }

    public StackedExceptionMessage(Map<String, Object> exceptionArguments, List<ExceptionMessage> exceptionStack) {
        super(exceptionArguments);
        this.exceptionStack = exceptionStack;
    }

    public <M extends ExceptionMessage> void addException(M message) {
        if (CollectionUtils.isEmpty(exceptionStack)) {
            exceptionStack = new ArrayList<>();
        }
        exceptionStack.add(message);
    }

    public List<ExceptionMessage> getExceptionStack() {
        return exceptionStack;
    }
}
