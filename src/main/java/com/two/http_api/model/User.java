package com.two.http_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {

    @Min(value = 1, message = "Invalid UID.")
    private int uid;

    private Integer pid;

    private Integer cid;

    @Email(message = "You must provide a valid email.")
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Age(13)
    private LocalDate dob;

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
