package com.pragma.powerup.usermicroservice.adapters.driving.http.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<LegalAgeOrNotNull, LocalDate> {

    @Override
    public boolean isValid(LocalDate fechaNacimiento, ConstraintValidatorContext context) {
        if(fechaNacimiento == null) return false;
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
}
