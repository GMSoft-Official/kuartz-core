package com.kuartz.core.common.validation.validator;

import com.kuartz.core.common.validation.Tckn;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TcknValidator implements ConstraintValidator<Tckn, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        if (StringUtils.containsWhitespace(value)) {
            return false;
        }

        if (!value.matches("^([1-9]{1}[0-9]{10})$")) {
            return false;
        }


        if (value.length() == 11) {
            int totalOdd = 0;

            int totalEven = 0;

            for (int i = 0; i < 9; i++) {
                int val = Integer.parseInt(value.substring(i, i + 1));

                if (i % 2 == 0) {
                    totalOdd += val;
                } else {
                    totalEven += val;
                }
            }

            int total = totalOdd + totalEven + Integer.parseInt(value.substring(9, 10));

            int lastDigit = total % 10;

            if (value.substring(10).equals(String.valueOf(lastDigit))) {
                int check = (totalOdd * 7 - totalEven) % 10;

                if (value.substring(9, 10).equals(String.valueOf(check))) {
                    return true;
                }
            }
        }

        return false;
    }
}