package com.imooc.exception;

public class RegistException extends Exception {

    public RegistException() {
        super();
    }

    public RegistException(String message) {
        super(message);
    }

    public RegistException(String message, Throwable cause) {
        super(message, cause);
    }
}
