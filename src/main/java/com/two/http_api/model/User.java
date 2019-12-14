package com.two.http_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Wither;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Wither
public class User {

    @Min(value = 1, message = "Invalid UID.")
    private int uid;

    private Integer pid;

    private Integer cid;

    @Email(message = "You must provide a valid email.")
    private String email;

    @NotEmpty(message = "First Name must be provided.")
    @Length(min = 2, message = "First Name must be at least 2 characters long.")
    private String firstName;

    @NotEmpty(message = "Last Name must be provided.")
    @Length(min = 2, message = "Last Name must be at least 2 characters long.")
    private String lastName;

    @Data
    @AllArgsConstructor
    public static class WithCredentials {
        @NotNull
        @Valid
        private User user;

        @NotEmpty(message = "You must provide a password.")
        @Length(min = 3, message = "You must provide a valid password.")
        private String password;
    }

}
