package com.domariev.financialproject.exception;

public class ResourceCreationException extends  RuntimeException{

    public ResourceCreationException(String message) {
        super(message);
    }

    public ResourceCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
