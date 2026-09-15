package org.exercise7.model.exceptions;

public class EmailAlreadyExistsException extends DomainException{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
