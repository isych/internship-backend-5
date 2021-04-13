package com.exadel.backendservice.exception.handler;

import com.exadel.backendservice.exception.ApiExceptionDto;
import com.exadel.backendservice.exception.ApiRequestException;
import com.exadel.backendservice.exception.ApiResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
       ApiExceptionDto apiExceptionDto = new ApiExceptionDto(
                e.getMessage(),
                e, HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
       );
       return new ResponseEntity<>(apiExceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ApiResponseException.class})
    public ResponseEntity<Object> handleApiResponseException(ApiResponseException e) {
        ApiExceptionDto apiExceptionDto = new ApiExceptionDto(
                e.getMessage(),
                e, HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
