package com.spring.boot.springsecurityproject3.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
