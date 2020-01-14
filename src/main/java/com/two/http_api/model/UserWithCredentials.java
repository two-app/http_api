package com.two.http_api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
public class UserWithCredentials extends User {
    @Email(message = "You must provide a valid email.")
    private final String email;

    @NotNull(message = "You must provide a password.")
    @Length(min = 3, message = "You must provide a valid password.")
    private final String password;

    public UserWithCredentials(int uid, Integer pid, Integer cid, String firstName, String lastName, String email, String password) {
        super(uid, pid, cid, firstName, lastName);
        this.email = email;
        this.password = password;
    }

    public static UserWithCredentials fromUser(User user, String email, String password) {
        return UserWithCredentials.builder()
                .uid(user.getUid())
                .pid(user.getPid())
                .cid(user.getCid())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(email)
                .password(password)
                .build();
    }
}
