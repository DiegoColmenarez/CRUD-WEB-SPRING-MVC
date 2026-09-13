package org.exercise7.model.exceptions;

public class InvalidUserEmailException extends DomainException{
    public InvalidUserEmailException(String message) {
        super(message);
    }

    private static final String MESSAGE_INVALID = "Email is Invalid, it's not a Email";
    private static final String MESSAGE_INVALID_EMPTY = "Email is Invalid, It's empty.";
    private static final String MESSAGE_INVALID_NULL = "Email is Invalid, It's NULL.";
    private static final String MESSAGE_EMAIL_EXIST = "Email is Invalid, This email is already registered.";

    public static InvalidUserEmailException becauseFormatIsInvalid(){
        return new InvalidUserEmailException(MESSAGE_INVALID);
    }
}
