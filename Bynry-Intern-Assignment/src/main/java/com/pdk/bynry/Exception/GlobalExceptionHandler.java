package com.pdk.bynry.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.getStatus().value());
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        errorResponse.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}