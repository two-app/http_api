package com.two.http_api.api;

import com.two.http_api.model.User;
import org.springframework.http.ResponseEntity;

public interface UserServiceContract {
    ResponseEntity<User> getUser(String email);
}