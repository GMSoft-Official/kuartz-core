package com.kuartz.core.common.validation;

import com.kuartz.core.common.validation.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {EmailValidator.class})
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    String message() default "Geçersiz E-Posta";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
