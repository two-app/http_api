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

class UserWithCredentialsTest {
    Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("it should fail if the email is invalid")
    void badEmail() {
        UserWithCredentials userWithCredentials = validUser().email("bar").build();
        assertFailureMessage(userWithCredentials, "You must provide a valid email.");
    }

    @Test
    @DisplayName("it should fail if no password is provided")
    void noPassword() {
        UserWithCredentials userWithCredentials = validUser().password(null).build();
        assertFailureMessage(userWithCredentials, "You must provide a password.");
    }

    @Test
    @DisplayName("it should fail if the password is < 2 in length")
    void badPassword() {
        UserWithCredentials userWithCredentials = validUser().password("b").build();
        assertFailureMessage(userWithCredentials, "You must provide a valid password.");
    }

    UserWithCredentials.UserWithCredentialsBuilder<?, ?> validUser() {
        return UserWithCredentials.builder().uid(1).pid(2).cid(3).firstName("Two").lastName("TwoL")
                .email("two@two.com").password("Str0ngPassw0rd");
    }

    void assertFailureMessage(UserWithCredentials user, String expectedMessage) {
        val failures = validator.validate(user);
        assertThat(firstMessage(failures)).isEqualTo(expectedMessage);
    }

    <T> String firstMessage(Set<ConstraintViolation<T>> violations) {
        return violations.iterator().next().getMessage();
    }
}