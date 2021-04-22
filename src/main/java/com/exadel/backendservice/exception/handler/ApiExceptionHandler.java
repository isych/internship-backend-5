package com.exadel.backendservice.exception.handler;

import com.exadel.backendservice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {DBNotUniqueValueForUniqueFieldException.class})
    public ResponseEntity<Object> handleDBNotUniqueValueForUniqueFieldException(DBNotUniqueValueForUniqueFieldException e) {
        ExceptionDto exceptionDto = new ExceptionDto(
                e.getMessage(),
                e, HttpStatus.CONFLICT,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {CannotUploadFileException.class})
    public ResponseEntity<Object> handleCannotUploadFileException(CannotUploadFileException e) {
        ExceptionDto exceptionDto = new ExceptionDto(
                e.getMessage(),
                e, HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UnsupportedMediaFormatException.class})
    public ResponseEntity<Object> handleUnsupportedMediaFormatException(UnsupportedMediaFormatException e) {
        ExceptionDto exceptionDto = new ExceptionDto(
                e.getMessage(),
                e, HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionDto, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(value = {DBNotFoundException.class})
    public ResponseEntity<Object> handleDBNotFoundException(DBNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto(
                e.getMessage(),
                e, HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ApiResponseException.class})
    public ResponseEntity<Object> handleApiResponseException(ApiResponseException e) {
        ExceptionDto exceptionDto = new ExceptionDto(
                e.getMessage(),
                e, HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        ExceptionDto exceptionDto = new ExceptionDto(
                e.getMessage(),
                e, HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {SameEventStatusException.class})
    public ResponseEntity<Object> handleEventsIsPublishedException(SameEventStatusException e) {
        ExceptionDto exceptionDto = new ExceptionDto(
                e.getMessage(),
                e, HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
