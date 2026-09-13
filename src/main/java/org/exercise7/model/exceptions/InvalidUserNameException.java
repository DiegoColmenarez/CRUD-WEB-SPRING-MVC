package org.exercise7.model.exceptions;

public class InvalidUserNameException extends DomainException{
    public InvalidUserNameException(String message) {
        super(message);
    }

    private static String MESSAGE_INVALID = "The name is invalid";
    private static String MESSAGE_INVALID_EMPTY = "The name is empty";

}
