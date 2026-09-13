package org.exercise7.model.exceptions;

public class InvalidUserEmailException extends DomainException{
    public InvalidUserEmailException(String message) {
        super(message);
    }
}
