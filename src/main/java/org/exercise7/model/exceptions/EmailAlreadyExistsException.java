package org.exercise7.model.exceptions;

public class EmailAlreadyExistsException extends DomainException{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    private static final String MESSAGE_EMAIL = "The email address you are trying to enter" +
            " already exists in the database.";

    public static EmailAlreadyExistsException becauseEmailAlredyExist(){
        return new EmailAlreadyExistsException(MESSAGE_EMAIL);
    }
}
