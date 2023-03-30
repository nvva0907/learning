package com.learning.domains.configurations.annotation_config;

import com.learning.domains.annotations.ValidDoubleType;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfigValidDoubleType implements ConstraintValidator<ValidDoubleType, Double> {
    private ValidDoubleType validDoubleType;

    @Override
    public void initialize(ValidDoubleType constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (!ObjectUtils.isEmpty(value)) {
            return value >= 0D;
        }
        return false;
    }
}
