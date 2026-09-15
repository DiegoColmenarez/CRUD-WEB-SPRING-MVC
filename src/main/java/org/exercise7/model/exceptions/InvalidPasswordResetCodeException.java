package org.exercise7.model.exceptions;

public class InvalidPasswordResetCodeException extends DomainException {
    public InvalidPasswordResetCodeException(String message) {
        super(message);
    }

    private static final String MESSAGE_EXPIRED = "Reset code has expired, please request a new one";
    private static final String MESSAGE_ALREADY_USED = "Reset code has already been used";
    private static final String MESSAGE_NOT_FOUND = "Reset code does not exist or is invalid";
}
