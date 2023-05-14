package com.pragma.powerup.usermicroservice.adapters.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<LegalAge, LocalDate> {
    @Override
    public void initialize(LegalAge constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate fechaNacimiento, ConstraintValidatorContext context) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
}
