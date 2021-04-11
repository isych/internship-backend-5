package com.exadel.backendservice.exception;

public class ApiRequestExceptionDto extends RuntimeException{
    public ApiRequestExceptionDto(String message) {
        super(message);
    }

    public ApiRequestExceptionDto(String message, Throwable cause) {
        super(message, cause);
    }
}
