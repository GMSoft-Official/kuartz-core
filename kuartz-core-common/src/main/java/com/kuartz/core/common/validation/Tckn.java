package com.kuartz.core.common.validation;


import com.kuartz.core.common.validation.validator.TcknValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {TcknValidator.class})
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Tckn {
    String message() default "Geçersiz TCKN";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
