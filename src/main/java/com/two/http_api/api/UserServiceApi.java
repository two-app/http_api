package com.two.http_api.api;

import com.netflix.discovery.EurekaClient;
import com.two.http_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceApi implements UserServiceContract {

    private final EurekaClient client;

    @Autowired
    public UserServiceApi(@Qualifier("eurekaClient") EurekaClient client) {
        this.client = client;
    }

    @Override
    public ResponseEntity<User> getUser(String email) {
        return new RestTemplate().getForEntity(getUserServiceUrl() + "user?email=" + email, User.class);
    }

    private String getUserServiceUrl() {
        return client.getNextServerFromEureka("service-users", false).getHomePageUrl();
    }

}
