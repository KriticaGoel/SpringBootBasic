package com.kritica.jpa.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalException {

@ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> exception(ResponseStatusException e) {
    return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
}

@ExceptionHandler(APIException.class)
    public ResponseEntity<String> apiException(APIException e) {
    return new ResponseEntity<>(e.getMsg(), e.getCode());
}
}
