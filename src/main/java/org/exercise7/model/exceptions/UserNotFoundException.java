package org.exercise7.model.exceptions;

public class UserNotFoundException extends DomainException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
