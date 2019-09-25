package com.two.http_api.model;

import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Value
public class User {

    private final int uid;
    private Integer pid;
    private Integer cid;

    private final String email;
    private final int age;
    private final String name;

    /**
     * Used for the initial register of a user in the authentication service.
     */
    @Value
    public static class Credentials {
        @Min(value = 1, message = "UID must be greater than one.")
        private final int uid;

        @NotEmpty(message = "You must provide a password.")
        @Length(min = 3, message = "Your password is too short.")
        private final String rawPassword;
    }

}
