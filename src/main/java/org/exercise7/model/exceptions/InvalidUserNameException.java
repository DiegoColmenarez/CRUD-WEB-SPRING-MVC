package org.exercise7.model.exceptions;

public class InvalidUserNameException extends DomainException {
    public InvalidUserNameException(String message) {
        super(message);
    }

    private static final String MESSAGE_INVALID = "The name is invalid";
    private static final String MESSAGE_INVALID_EMPTY = "The name is empty";
    private static final String MESSAGE_INVALID_NULL = "The name is null";

    public static InvalidUserNameException becauseContainsInvalidCharacters() {
        return new InvalidUserNameException(MESSAGE_INVALID);
    }

    public static InvalidUserNameException becauseIsEmpty() {
        return new InvalidUserNameException(MESSAGE_INVALID_EMPTY);
    }

    public static InvalidUserNameException becauseIsNull() {
        return new InvalidUserNameException(MESSAGE_INVALID_NULL);
    }
}