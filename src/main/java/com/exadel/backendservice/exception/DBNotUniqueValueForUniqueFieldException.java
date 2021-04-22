package com.exadel.backendservice.exception;

public class DBNotUniqueValueForUniqueFieldException extends RuntimeException {
    public DBNotUniqueValueForUniqueFieldException() {
        super();
    }

    public DBNotUniqueValueForUniqueFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBNotUniqueValueForUniqueFieldException(String message) {
        super(message);
    }

    public DBNotUniqueValueForUniqueFieldException(Throwable cause) {
        super(cause);
    }

    protected DBNotUniqueValueForUniqueFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
