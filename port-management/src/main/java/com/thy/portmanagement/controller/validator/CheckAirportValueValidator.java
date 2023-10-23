package com.thy.portmanagement.controller.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CheckAirportValueValidator implements ConstraintValidator<CheckAirportValue, String> {
    private static final Logger logger = LoggerFactory.getLogger(CheckAirportValueValidator.class);

    @Override
    public void initialize(CheckAirportValue constraintAnnotation) {

        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String airportValue, ConstraintValidatorContext context) {

        if (Strings.isEmpty(airportValue) || airportValue.length() < 3) {
            logger.error("Airport value must consist of at least 3 characters");
            return false;
        }

        return true;
    }
}
