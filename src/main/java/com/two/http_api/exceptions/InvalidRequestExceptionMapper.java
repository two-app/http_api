package com.two.http_api.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class InvalidRequestExceptionMapper {

    private static final Logger logger = LoggerFactory.getLogger(InvalidRequestExceptionMapper.class);

    /**
     * @param e Constraint Violation Exception, typically raised by JavaX Validation Constraints.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse error(ConstraintViolationException e) {
        logger.warn("[400] Converting multiple Constraint Violations into 400 BAD REQUEST.", e);
        return new ExceptionResponse(
                e.getConstraintViolations().iterator().next().getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * @param e Method Argument Not Valid Exception, typically raised by JavaX @Valid annotation.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse error(MethodArgumentNotValidException e) {
        logger.warn("[400] Converting singular Constraint Violation into 400 BAD REQUEST.", e);
        return new ExceptionResponse(
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    /**
     * @param e Http Message Not Readable Exception, typically occurring if the response body is malformed or not present.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse error(HttpMessageNotReadableException e) throws ResponseStatusException {
        logger.warn("[400] Badly formed HTTP request received.", e);
        return new ExceptionResponse("Badly formed HTTP request.", HttpStatus.BAD_REQUEST);
    }

}
