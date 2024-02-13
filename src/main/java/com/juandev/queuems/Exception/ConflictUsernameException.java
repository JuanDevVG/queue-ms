package com.juandev.queuems.Exception;

public class ConflictUsernameException extends RuntimeException {
    public ConflictUsernameException (String message) {
        super(message);
    }
}
