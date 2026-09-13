package org.exercise7.model.exceptions;

public class InvalidUserNameException extends DomainException{
    public InvalidUserNameException(String message) {
        super(message);
    }

    private static String MESSAGE_INVALID = "The name is invalid";
    private static String MESSAGE_INVALID_EMPTY = "The name is empty";


    public static InvalidUserNameException becauseContainsInvalidCharacters(){
        return new InvalidUserNameException(MESSAGE_INVALID);
    }

    public static InvalidUserNameException becauseIsEmpty(){
        return new InvalidUserNameException(MESSAGE_INVALID_EMPTY);
    }
}
