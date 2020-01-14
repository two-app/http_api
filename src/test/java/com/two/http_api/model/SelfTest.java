package com.two.http_api.model;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SelfTest {
    Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("it should fail if the uid is 0")
    void badUID() {
        // regression test for inheritance based validation
        Self self = validSelf().uid(0).build();
        assertFailureMessage(self, "Invalid UID.");
    }

    @Test
    @DisplayName("it should fail if the email is invalid")
    void badEmail() {
        Self self = validSelf().email("bla").build();
        assertFailureMessage(self, "You must provide a valid email.");
    }

    void assertFailureMessage(Self self, String expectedMessage) {
        val failures = validator.validate(self);
        assertThat(firstMessage(failures)).isEqualTo(expectedMessage);
    }

    Self.SelfBuilder<?, ?> validSelf() {
        return Self.builder().uid(1).pid(2).cid(3).firstName("Two").lastName("TwoL").email("two@two.com");
    }

    <T> String firstMessage(Set<ConstraintViolation<T>> violations) {
        return violations.iterator().next().getMessage();
    }
}