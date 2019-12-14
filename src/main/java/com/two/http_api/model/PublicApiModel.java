package com.two.http_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PublicApiModel {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLogin {
        @NotEmpty(message = "Valid email must be provided.")
        @Email(message = "Valid email must be provided.")
        String email;

        @NotEmpty(message = "Valid password must be provided.")
        @Length(min = 3, message = "Valid password must be provided.")
        String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class UserRegistration {
        @NotEmpty(message = "Email must be provided.")
        @Email(message = "Email must be valid.")
        private String email;

        @NotEmpty(message = "Password must be provided.")
        @Length(min = 5, message = "Password must be at least 5 characters long.")
        private String password;

        @NotEmpty(message = "Name must be provided.")
        @Length(min = 5, message = "Name must be at least 5 characters long.")
        private String name;

        @NotEmpty(message = "First Name must be provided.")
        @Length(min = 2, message = "First Name must be at least 2 characters long.")
        private String firstName;

        @NotEmpty(message = "Last Name must be provided.")
        @Length(min = 2, message = "Last Name must be at least 2 characters long.")
        private String lastName;

        @NotNull
        @AssertTrue
        private boolean acceptedTerms;

        @NotNull
        @AssertTrue
        private boolean ofAge;
    }


}
