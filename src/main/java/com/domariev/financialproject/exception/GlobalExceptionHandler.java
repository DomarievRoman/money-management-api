package com.domariev.financialproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<CustomException> handleResourceNotFoundException(ResourceNotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        CustomException customException = new CustomException(ex.getMessage(), httpStatus, LocalDateTime.now());
        log.warn("handleResourceNotFoundException(): exception founded", ex);
        return new ResponseEntity<>(customException, httpStatus);
    }

    @ExceptionHandler(value = ResourceCreationException.class)
    public ResponseEntity<CustomException> handleResourceCreationException(ResourceNotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        CustomException customException = new CustomException(ex.getMessage(), httpStatus, LocalDateTime.now());
        log.warn("handleResourceCreationException(): exception founded", ex);
        return new ResponseEntity<>(customException, httpStatus);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CustomException> handleValidationException(MethodArgumentNotValidException ex) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomException customException = new CustomException(
                ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), httpStatus, LocalDateTime.now());
        log.warn("handleValidationException(): exception founded", ex);
        return new ResponseEntity<>(customException, httpStatus);
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        CustomException customException = new CustomException(ex.getMessage(), httpStatus, LocalDateTime.now());
        log.warn("handleGlobalException(): exception founded", ex);
        return new ResponseEntity<>(customException, httpStatus);
    }
}
