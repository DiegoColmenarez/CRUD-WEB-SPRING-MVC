package org.exercise7.model.exceptions;

public class ResourceNotFoundException extends DomainException {
    protected ResourceNotFoundException(String message) {
        super(message);
    }
}
