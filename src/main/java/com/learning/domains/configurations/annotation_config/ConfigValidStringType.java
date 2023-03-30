package com.learning.domains.configurations.annotation_config;

import com.learning.domains.annotations.ValidStringType;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfigValidStringType implements ConstraintValidator<ValidStringType, String> {
    @Override
    public void initialize(ValidStringType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (ObjectUtils.isEmpty(value))
            return false;
        if (!ObjectUtils.isEmpty(value))
            return !value.trim().isEmpty();
        return true;

    }
}