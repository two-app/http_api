package com.two.http_api.api;

import com.netflix.discovery.EurekaClient;
import com.two.http_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class UserServiceApi implements UserServiceContract {

    private final EurekaClient client;

    @Autowired
    public UserServiceApi(@Qualifier("eurekaClient") EurekaClient client) {
        this.client = client;
    }

    @Override
    public User getUser(String email) {
        WebClient webClient = this.getUserServiceClient();
        WebClient.RequestHeadersSpec request = webClient.get()
                .uri(builder -> builder.queryParam("email", email).build());

        return request.retrieve().bodyToMono(User.class).block(Duration.ofSeconds(15));
    }

    private WebClient getUserServiceClient() {
        String serviceUrl = client.getNextServerFromEureka("service-users", false).getHomePageUrl();
        return WebClient.create(serviceUrl);
    }

}
