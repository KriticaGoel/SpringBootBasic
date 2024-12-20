package com.kritica.springbatchjpa.Exception;

import org.springframework.batch.core.JobExecutionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(JobExecutionException.class)
    public JobExecutionException jobExecutionException(JobExecutionException e) {
        return e;
    }

}
