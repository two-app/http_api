package com.two.http_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class User {

    @Min(value = 1, message = "Invalid UID.")
    private int uid;

    private Integer pid;

    private Integer cid;

    @Email(message = "You must provide a valid email.")
    private String email;

    @Min(value = 13, message = "You must be at least 13.")
    @Max(value = 99, message = "You can't be over 99.")
    private int age;

    @NotEmpty(message = "You must provide a name.")
    private String name;

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
