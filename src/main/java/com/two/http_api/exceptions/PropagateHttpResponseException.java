package com.two.http_api.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

public class PropagateHttpResponseException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(PropagateHttpResponseException.class);
    private final ExceptionResponse exceptionResponse;

    public PropagateHttpResponseException(ExceptionResponse exceptionResponse) {
        this.exceptionResponse = exceptionResponse;
    }

    public PropagateHttpResponseException(WebClientResponseException e) {
        try {
            this.exceptionResponse = new ObjectMapper().readValue(
                    e.getResponseBodyAsByteArray(),
                    ExceptionResponse.class
            );
        } catch (IOException ioe) {
            logger.error("Failed to read exception response from REST response.", e, ioe);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ExceptionResponse getExceptionResponse() {
        return exceptionResponse;
    }
}
