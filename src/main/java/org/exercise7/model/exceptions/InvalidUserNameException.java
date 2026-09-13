package org.exercise7.model.exceptions;

public class InvalidUserNameException extends DomainException{
    public InvalidUserNameException(String message) {
        super(message);
    }

}
