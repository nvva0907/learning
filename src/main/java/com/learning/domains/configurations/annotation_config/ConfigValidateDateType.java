package com.learning.domains.configurations.annotation_config;

import com.learning.domains.annotations.ValidateDateType;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class ConfigValidateDateType implements ConstraintValidator<ValidateDateType, Date> {

    @Override
    public void initialize(ValidateDateType constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (ObjectUtils.isEmpty(value)) {
            return false;
        }
        return !value.before(new Date());
    }
}
