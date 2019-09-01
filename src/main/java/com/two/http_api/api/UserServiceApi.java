package com.two.http_api.api;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.two.http_api.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceApi {

    private final EurekaClient client;

    public UserServiceApi(@Qualifier("eurekaClient") EurekaClient client) {
        this.client = client;
    }

    public ResponseEntity<User> getUser(int uid) {
        return new RestTemplate().getForEntity(getUserServiceUrl() + "?uid=" + uid, User.class);
    }

    public ResponseEntity<User> getUser(String email) {
        return new RestTemplate().getForEntity(getUserServiceUrl() + "?email=" + email, User.class);
    }

    private String getUserServiceUrl() {
        String url = client.getNextServerFromEureka("service-users", false).getHomePageUrl();
        return url + "user";
    }

}
