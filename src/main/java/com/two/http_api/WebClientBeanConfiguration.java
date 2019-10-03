package com.two.http_api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientBeanConfiguration {

    @Bean
    @Primary
    public WebClient getWebClient() {
        return WebClient.create();
    }

}
