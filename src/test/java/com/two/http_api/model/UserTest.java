package com.two.http_api.model;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Nested
    class BaseUser {
        @Test
        @DisplayName("it should fail if the uid is < 1")
        void badUID() {
            User user = validUser().uid(0).build();
            assertFailureMessage(user, "Invalid UID.");
        }

        @Test
        @DisplayName("it should fail if the pid is < 1")
        void badPID() {
            User user = validUser().pid(0).build();
            assertFailureMessage(user, "Invalid PID.");
        }

        @Test
        @DisplayName("it should fail if the cid is < 1")
        void badCID() {
            User user = validUser().cid(0).build();
            assertFailureMessage(user, "Invalid CID.");
        }

        @Test
        @DisplayName("it should fail if the first name is < 2 characters in length")
        void badFirstName() {
            User user = validUser().firstName("g").build();
            assertFailureMessage(user, "First name must be at least 2 characters long.");
        }

        @Test
        @DisplayName("it should fail is the last name is < 2 characters in length")
        void badLastName() {
            User user = validUser().lastName("g").build();
            assertFailureMessage(user, "Last name must be at least 2 characters long.");
        }
    }

    User.UserBuilder<?, ?> validUser() {
        return User.builder().uid(1).pid(2).cid(3).firstName("Two").lastName("TwoL");
    }

    void assertFailureMessage(User user, String expectedMessage) {
        val failures = validator.validate(user);
        assertThat(firstMessage(failures)).isEqualTo(expectedMessage);
    }

    <T> String firstMessage(Set<ConstraintViolation<T>> violations) {
        return violations.iterator().next().getMessage();
    }
}