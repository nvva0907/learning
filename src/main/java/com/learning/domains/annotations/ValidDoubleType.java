package com.learning.domains.annotations;

import com.learning.domains.configurations.annotation_config.ConfigValidDoubleType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( {ElementType.FIELD, ElementType.METHOD})
@Retention( RetentionPolicy.RUNTIME)
@Constraint( validatedBy = ConfigValidDoubleType.class)
public @interface ValidDoubleType {
    String message() default "{validation.validName}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
