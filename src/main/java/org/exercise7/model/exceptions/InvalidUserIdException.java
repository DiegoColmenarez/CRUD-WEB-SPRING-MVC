package org.exercise7.model.exceptions;

public class InvalidUserIdException extends DomainException {
    public InvalidUserIdException(String message) {
        super(message);
    }
}
