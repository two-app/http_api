package com.two.http_api.model;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class Partner extends User {
    public Partner(int uid, Integer pid, Integer cid, String firstName, String lastName) {
        super(uid, pid, cid, firstName, lastName);
    }
}
