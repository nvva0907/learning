package com.learning.domains.annotations;

import com.learning.domains.configurations.annotation_config.ConfigValidateDateType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfigValidateDateType.class)
public @interface ValidateDateType {
    String message() default "{validation.validName}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
