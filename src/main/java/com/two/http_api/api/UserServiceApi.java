package com.two.http_api.api;

import com.two.http_api.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserServiceApi {

    private final String userServiceUrl = "http://localhost:8084";

    public ResponseEntity<User> getUser(int uid) {
        return new RestTemplate().getForEntity(userServiceUrl + "?uid=" + uid, User.class);
    }

    public ResponseEntity<User> getUser(String email) {
        return new RestTemplate().getForEntity(userServiceUrl + "?email=" + email, User.class);
    }

}
