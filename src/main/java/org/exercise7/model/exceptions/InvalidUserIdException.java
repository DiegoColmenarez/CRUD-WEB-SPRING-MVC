package org.exercise7.model.exceptions;

public class InvalidUserIdException extends DomainException {
    public InvalidUserIdException(String message) {
        super(message);
    }

    private static final String MESSAGE_INVALID_ID = "The ID is invalid";

    public static InvalidUserIdException becauseIdIsInvalid(){
        return new InvalidUserIdException(MESSAGE_INVALID_ID);
    }
}
