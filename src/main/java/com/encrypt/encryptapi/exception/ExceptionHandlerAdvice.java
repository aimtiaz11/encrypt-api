package com.encrypt.encryptapi.exception;

import com.encrypt.encryptapi.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleBadRequest(
            RuntimeException ex, WebRequest request) {
        ApiError errorResponseBody = new ApiError(HttpStatus.BAD_REQUEST, "Bad Request Exception",
                Collections.singletonList(ex.getMessage()));

        return handleExceptionInternal(ex, errorResponseBody,
                new HttpHeaders(), HttpStatus.BAD_REQUEST,
                request);
    }
    
}
