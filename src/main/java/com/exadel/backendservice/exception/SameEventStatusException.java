package com.exadel.backendservice.exception;

public class SameEventStatusException extends RuntimeException {

    public SameEventStatusException() {
        super();
    }

    public SameEventStatusException(String message) {
        super(message);
    }

    public SameEventStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameEventStatusException(Throwable cause) {
        super(cause);
    }

    protected SameEventStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
