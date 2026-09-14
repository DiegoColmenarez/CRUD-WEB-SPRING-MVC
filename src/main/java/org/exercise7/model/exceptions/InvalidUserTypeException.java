package org.exercise7.model.exceptions;

public class InvalidUserTypeException extends DomainException {
    public InvalidUserTypeException(String message) {
        super(message);
    }

    private static final String MESSAGE_INVALID = "User type is invalid";
    private static final String MESSAGE_INVALID_NULL = "User type is invalid, it's NULL";

    public static InvalidUserTypeException becauseValueIsInvalid() {
        return new InvalidUserTypeException(MESSAGE_INVALID);
    }

    public static InvalidUserTypeException becauseIsNull() {
        return new InvalidUserTypeException(MESSAGE_INVALID_NULL);
    }
}