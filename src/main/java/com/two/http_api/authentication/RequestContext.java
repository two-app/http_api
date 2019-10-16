package com.two.http_api.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestContext {

    private final int uid;
    private final Integer pid;
    private final Integer cid;
    private final boolean isConnectToken;
    private final String connectCode;

    private static final Logger logger = LoggerFactory.getLogger(RequestContext.class);

    /**
     * Reads, extracts, and parses a JWT stored in an Authorization header. Must of Bearer type.
     *
     * @param request a HTTP request containing an Authorization header.
     * @return a Request Context.
     */
    public static RequestContext from(HttpServletRequest request) {
        String jwt = extractJwtFromHeader(request.getHeader("Authorization"));
        DecodedJWT decodedJWT = JWT.decode(jwt);

        return new RequestContext(
                decodedJWT.getClaim("uid").asInt(),
                decodedJWT.getClaim("pid").asInt(),
                decodedJWT.getClaim("cid").asInt(),
                !decodedJWT.getClaim("connectCode").isNull(),
                decodedJWT.getClaim("connectCode").asString()
        );
    }

    /**
     * @return a non-parsed or validated JWT extracted from the authorization header.
     * @throws IllegalArgumentException if the authorization header is not present, or invalid.
     */
    private static String extractJwtFromHeader(String authorizationHeader) {
        logger.info("Attempting JWT extraction on header: {}.", authorizationHeader);

        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            logger.warn("No 'Authorization' header present in request.");
            throw new IllegalArgumentException("Authorization header is not present.");
        }

        String[] parts = authorizationHeader.split(" ");

        if (parts.length != 2) {
            logger.warn("Authorization header is present, but not in two parts: {}.", authorizationHeader);
            throw new IllegalArgumentException("Authorization header is invalid.");
        }

        if (!parts[0].toLowerCase().startsWith("bearer")) {
            logger.warn("Authorization header is not of Bearer type.");
            throw new IllegalArgumentException("Authorization header not of Bearer type.");
        }

        logger.info("Extracted JWT {} from request.", parts[1]);
        return parts[1];
    }

}
