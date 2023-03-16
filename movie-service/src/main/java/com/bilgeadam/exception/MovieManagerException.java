package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class MovieManagerException extends RuntimeException{

    private final ErrorType errorType;
    public MovieManagerException(ErrorType errorType) {
        this.errorType = errorType;
    }
    public MovieManagerException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }
}
