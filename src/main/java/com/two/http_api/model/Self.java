package com.two.http_api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class Self extends User {
    @Email(message = "You must provide a valid email.")
    private final String email;

    public Self(int uid, Integer pid, Integer cid, String firstName, String lastName, String email) {
        super(uid, pid, cid, firstName, lastName);
        this.email = email;
    }

    public static Self fromUser(User user, String email) {
        return Self.builder()
                .uid(user.getUid())
                .pid(user.getPid())
                .cid(user.getCid())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(email)
                .build();
    }
}
