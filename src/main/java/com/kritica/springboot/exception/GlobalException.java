package com.kritica.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        HashMap<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error)->{
           String fieldName=  ((FieldError) error).getField();
           String errorMessage = error.getDefaultMessage();
           errors.put(fieldName,errorMessage);
        });


        return new ResponseEntity<>(errors, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ResourcesNotFound.class)
    public ResponseEntity<String>  resourceNotFound(ResourcesNotFound e){

        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> apiException(APIException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_GATEWAY);

    }
}
