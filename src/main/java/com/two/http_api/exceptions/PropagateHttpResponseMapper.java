package com.two.http_api.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class PropagateHttpResponseMapper {

    private static final Logger logger = LoggerFactory.getLogger(PropagateHttpResponseMapper.class);


    /**
     * The exception is replicated and returned as a HTTP response with the exact same data.
     */
    @ExceptionHandler(PropagateHttpResponseException.class)
    public ResponseEntity<ExceptionResponse> error(PropagateHttpResponseException e) {
        ExceptionResponse response = e.getExceptionResponse();
        HttpStatus status = HttpStatus.valueOf(response.getStatus());

        logger.warn("Propagating HTTP response with status {}.", status, e);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> error(ResponseStatusException e) {
        ExceptionResponse response = new ExceptionResponse(e.getReason(), e.getStatus());

        logger.warn("Mapping Response Status Exception to Exception Response.");
        return new ResponseEntity<>(response, e.getStatus());
    }
}
