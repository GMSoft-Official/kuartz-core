package com.kuartz.core.data.jpa;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(rollbackFor = {Exception.class})
public @interface TransactionalRollback {
}
