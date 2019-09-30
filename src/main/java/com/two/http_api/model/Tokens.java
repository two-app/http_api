package com.two.http_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Tokens {
    private final String refreshToken;
    private final String accessToken;
}
