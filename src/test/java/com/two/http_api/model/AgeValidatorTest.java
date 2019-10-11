package com.two.http_api.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AgeValidatorTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("it should fail if the age is under 13")
    void under13() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -12);

        User user = new User(
                2,
                3,
                4,
                "gerry@two.com",
                LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate(),
                "Gerry"
        );

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations).hasSize(1);
        String message = violations.iterator().next().getMessage();
        assertThat(message).isEqualTo("Age must be greater than 13.");
    }

    @Test
    @DisplayName("it should pass if the user is older than 13")
    void validAge() {
        User user = new User(
                2,
                3,
                4,
                "gerry@two.com",
                LocalDate.parse("1997-08-21"),
                "Gerry"
        );

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations).isEmpty();
    }

}