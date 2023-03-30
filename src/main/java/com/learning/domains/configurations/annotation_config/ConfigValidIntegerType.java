package com.learning.domains.configurations.annotation_config;

import com.learning.domains.annotations.ValidIntegerType;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfigValidIntegerType implements ConstraintValidator<ValidIntegerType, Integer> {
    @Override
    public void initialize(ValidIntegerType validIntegerType) {

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (!ObjectUtils.isEmpty(integer)) {
            return integer >= 1;
        }
        return false;
    }
}
