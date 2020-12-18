package com.kuartz.core.common.enumaration;

/**
 * Base enum interface to be used when stored in database
 */
public interface KzEnum<T> {

    String label();

    T code();

    KzEnum<T> getByCode(int code);
}
