package com.thy.portmanagement.controller.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckAirportValueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAirportValue {
    String message() default "Airport name must consist of at least 3 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
