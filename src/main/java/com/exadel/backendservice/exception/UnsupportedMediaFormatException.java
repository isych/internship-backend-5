package com.exadel.backendservice.exception;

public class UnsupportedMediaFormatException extends RuntimeException{
    public UnsupportedMediaFormatException() {
        super();
    }

    public UnsupportedMediaFormatException(String message) {
        super(message);
    }

    public UnsupportedMediaFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedMediaFormatException(Throwable cause) {
        super(cause);
    }

    protected UnsupportedMediaFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
