package org.exercise7.model.exceptions;

public class InvalidUserPasswordException extends DomainException{
    public InvalidUserPasswordException(String message) {
        super(message);
    }

}
