package com.learning.domains.annotations;

import com.learning.domains.configurations.annotation_config.ConfigValidIntegerType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfigValidIntegerType.class)
public @interface ValidIntegerType {

    String message() default "{validation.validName}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
