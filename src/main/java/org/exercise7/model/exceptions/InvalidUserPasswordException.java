package org.exercise7.model.exceptions;

public class InvalidUserPasswordException extends DomainException{
    public InvalidUserPasswordException(String message) {
        super(message);
    }


    private static final String MESSAGE_INVALID = "The password is invalid, it's empty";
    private static final String MESSAGE_INVALID_FORMAT = "The password is invalid," +
            " The password must contain at least: a special character, a number, a lowercase letter, " +
            "and an uppercase letter. It cannot contain spaces.";
    private static final String MESSAGE_INVALID_PASSWORD = "Incorrect password";


}
