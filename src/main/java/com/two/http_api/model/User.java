package com.two.http_api.model;

import com.two.http_api.validators.ValidId;
import com.two.http_api.validators.ValidName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class User {

    @ValidId(message = "Invalid UID.")
    private final int uid;

    @ValidId(message = "Invalid PID.")
    private final Integer pid;

    @ValidId(message = "Invalid CID.")
    private final Integer cid;

    @ValidName(message = "First name must be at least 2 characters long.")
    private final String firstName;

    @ValidName(message = "Last name must be at least 2 characters long.")
    private final String lastName;

}
