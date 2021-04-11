package com.exadel.backendservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiExceptionDto {
    private final String massage;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final LocalDateTime localDateTime;
}
