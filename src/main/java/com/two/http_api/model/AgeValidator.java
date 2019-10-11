package com.two.http_api.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {
    protected long minAge;

    @Override
    public void initialize(Age minAge) {
        this.minAge = minAge.value();
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) return false;
        LocalDate today = LocalDate.now();
        return ChronoUnit.YEARS.between(date, today) >= minAge;
    }
}