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
    public ResponseEntity<APIResponse>  resourceNotFound(ResourcesNotFound e){
        APIResponse apiResponse= new APIResponse();
        apiResponse.setMessage(e.getMessage());
        apiResponse.setStatus(false);
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> apiException(APIException e){
        APIResponse apiResponse= new APIResponse();
        apiResponse.setMessage(e.getMessage());
        apiResponse.setStatus(false);
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.BAD_GATEWAY);

    }
}
