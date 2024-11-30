package com.kritica.jpa.Exception;

import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException{
    private String msg;
    private HttpStatus code;

    public APIException(String msg, HttpStatus code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }
}
