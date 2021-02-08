package com.domariev.financialproject.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class CustomException {

    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;
}
