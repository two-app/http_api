package com.two.http_api.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private final String message;
    private final int status;
    private final Instant timestamp;

    @JsonCreator
    public ExceptionResponse(@JsonProperty("message") String message,
                             @JsonProperty("status") int status,
                             @JsonProperty("timestamp") String timestamp) {
        this(message, status, Instant.parse(timestamp));
    }

    public ExceptionResponse(String message, int status) {
        this(message, status, Instant.now());
    }

    public ExceptionResponse(String message, HttpStatus httpStatus) {
        this(message, httpStatus.value());
    }
}
