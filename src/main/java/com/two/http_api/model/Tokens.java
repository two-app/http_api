package com.two.http_api.model;

import lombok.Value;

@Value
public class Tokens {
    private final String refreshToken;
    private final String accessToken;
}
