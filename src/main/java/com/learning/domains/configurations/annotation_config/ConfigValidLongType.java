package com.learning.domains.configurations.annotation_config;

import com.learning.domains.annotations.ValidLongType;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfigValidLongType implements ConstraintValidator<ValidLongType, Long> {
    private ValidLongType validLongType;

    @Override
    public void initialize(ValidLongType constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (!ObjectUtils.isEmpty(value)) {
            return value >= 0L;
        }
        return false;
    }
}