package com.kuartz.core.common.exception;

public interface KzBaseException<M extends BaseExceptionMessage> {
    M getExceptionDetail();
}
