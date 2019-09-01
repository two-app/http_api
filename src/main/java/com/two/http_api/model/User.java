package com.two.http_api.model;

import lombok.Value;

@Value
public class User {

    private final int uid;
    private final Integer pid;
    private final Integer cid;

    private final String email;

}
