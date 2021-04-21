package com.exadel.backendservice.exception;

public class CannotUploadFileException extends RuntimeException {
    public CannotUploadFileException() {
        super();
    }

    public CannotUploadFileException(String message) {
        super(message);
    }

    public CannotUploadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotUploadFileException(Throwable cause) {
        super(cause);
    }

    protected CannotUploadFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
