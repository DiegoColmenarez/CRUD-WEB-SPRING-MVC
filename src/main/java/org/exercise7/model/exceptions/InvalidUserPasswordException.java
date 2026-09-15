package org.exercise7.model.exceptions;

public class InvalidUserPasswordException extends DomainException {
    public InvalidUserPasswordException(String message) {
        super(message);
    }

    private static final String MESSAGE_INVALID_PASSWORD = "Incorrect password";

    public static InvalidUserPasswordException becausePasswordIsInvalid() {
        return new InvalidUserPasswordException(MESSAGE_INVALID_PASSWORD);
    }
}