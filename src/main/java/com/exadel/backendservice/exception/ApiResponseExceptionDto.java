package com.exadel.backendservice.exception;

public class ApiResponseExceptionDto extends RuntimeException {
    public ApiResponseExceptionDto(String message) {
        super(message);
    }

    public ApiResponseExceptionDto(String message, Throwable cause) {
        super(message, cause);
    }
}
