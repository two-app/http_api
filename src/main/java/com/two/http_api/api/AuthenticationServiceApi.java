package com.two.http_api.api;

import com.netflix.discovery.EurekaClient;
import com.two.http_api.model.Tokens;
import com.two.http_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationServiceApi implements AuthenticationServiceContract {

    private final EurekaClient client;

    @Autowired
    public AuthenticationServiceApi(@Qualifier("eurekaClient") EurekaClient client) {
        this.client = client;
    }

    @Override
    public ResponseEntity<Tokens> storeCredentialsAndGenerateTokens(User.Credentials credentials) {
        return new RestTemplate().postForEntity(getUserServiceUrl() + "credentials", credentials, Tokens.class);
    }

    private String getUserServiceUrl() {
        return client.getNextServerFromEureka("service-authentication", false).getHomePageUrl();
    }
}
