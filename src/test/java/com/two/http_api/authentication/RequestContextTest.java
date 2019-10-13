package com.two.http_api.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestContextTest {

    @Test
    @DisplayName("it should successfully extract data for a valid JWT")
    void extractsBodyData() {
        String token = JWT.create().withClaim("uid", 1)
                .withClaim("pid", 2)
                .withClaim("cid", 3)
                .sign(Algorithm.HMAC256("asdasdhoashd"));

        RequestContext context = getRequestContextForAuthorizationHeader("Bearer " + token);

        assertThat(context.getUid()).isEqualTo(1);
        assertThat(context.getPid()).isEqualTo(2);
        assertThat(context.getCid()).isEqualTo(3);
        assertThat(context.isConnectToken()).isFalse();
        assertThat(context.getConnectCode()).isNull();
    }

    @Test
    @DisplayName("it should store the connect code if available")
    void withConnectCode() {
        String token = JWT.create().withClaim("uid", 1)
                .withClaim("connectCode", "testConnectCode")
                .sign(Algorithm.HMAC256("asdasdhoashd"));

        RequestContext context = getRequestContextForAuthorizationHeader("Bearer " + token);

        assertThat(context.getUid()).isEqualTo(1);
        assertThat(context.getPid()).isNull();
        assertThat(context.getCid()).isNull();
        assertThat(context.isConnectToken()).isTrue();
        assertThat(context.getConnectCode()).isEqualTo("testConnectCode");
    }

    @Test
    @DisplayName("it should throw an Illegal Argument Exception if the header is not present")
    void headerNotPresent() {
        assertThatThrownBy(() -> getRequestContextForAuthorizationHeader(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Authorization header is not present.");
    }

    @Test
    @DisplayName("it should throw an Illegal Argument Exception if the header is not in two parts")
    void wrongNumberOfParts() {
        assertThatThrownBy(() -> getRequestContextForAuthorizationHeader("xyz"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Authorization header is invalid.");
    }

    @Test
    @DisplayName("it should throw an Illegal Argument Exception if the header is not of Bearer type")
    void notBearerType() {
        assertThatThrownBy(() -> getRequestContextForAuthorizationHeader("NotBear xyz"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Authorization header not of Bearer type.");
    }

    RequestContext getRequestContextForAuthorizationHeader(String headerValue) {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn(headerValue);
        return RequestContext.from(request);
    }

}