package com.two.http_api.api;

import com.two.http_api.model.User;

public interface UserServiceContract {
    User getUser(String email);
}