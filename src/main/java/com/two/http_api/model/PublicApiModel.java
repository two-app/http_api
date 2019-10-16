package com.two.http_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

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

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @Age(13)
        private LocalDate dob;
    }


}
