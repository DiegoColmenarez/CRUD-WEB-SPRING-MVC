package org.exercise7.model.exceptions;

public class InvalidUserTypeException extends DomainException{
    public InvalidUserTypeException(String message) {
        super(message);
    }
}
