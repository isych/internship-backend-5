package com.exadel.backendservice.exception;

public class DBNotFoundException extends RuntimeException {

    public DBNotFoundException() {
        super();
    }

    public DBNotFoundException(String message) {
        super(message);
    }

    public DBNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DBNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
