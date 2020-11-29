package com.kuartz.core.common.validation.validator;

import com.kuartz.core.common.validation.Email;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        if (StringUtils.containsWhitespace(value)) {
            return false;
        }

        // if (! value.matches(
        //         "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")) {
        if (! value.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b")) {
            return false;
        }

        return true;
    }
}