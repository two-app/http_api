package com.two.http_api.api;

import com.netflix.discovery.EurekaClient;
import com.two.http_api.model.Tokens;
import com.two.http_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.URI;
import java.time.Duration;

@Service
public class AuthenticationServiceApi implements AuthenticationServiceContract {

    private final WebClient webClient;
    private final EurekaClient client;

    @Autowired
    @Lazy
    public AuthenticationServiceApi(@Qualifier("eurekaClient") EurekaClient client, WebClient webClient) {
        this.webClient = webClient;
        this.client = client;
    }

    /**
     * Performs a POST to the authentication service. The response is directly mapped to tokens.
     *
     * @param credentials to store in the authentication service.
     * @return either an access/refresh or connect/refresh pair of tokens.
     * @throws WebClientResponseException if there is a 4xx or 5xx response status.
     */
    @Override
    public Tokens storeCredentialsAndGenerateTokens(User.Credentials credentials) throws WebClientResponseException {
        String host = client.getNextServerFromEureka("service-authentication", false).getHomePageUrl();
        String path = host + "credentials";

        WebClient.RequestHeadersSpec request = webClient.post()
                .uri(path)
                .body(BodyInserters.fromObject(credentials));

        return request.retrieve().bodyToMono(Tokens.class).block(Duration.ofSeconds(15));
    }

}
