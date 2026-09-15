package org.exercise7.model.exceptions;

public class UserNotFoundException extends DomainException{
    public UserNotFoundException(String message) {
        super(message);
    }

    private static final String MESSAGE_USER_NOT_EXIST = "The user id: '%d' don't exist";
    private static final String MESSAGE_USER_EMAIL_NOT_EXIST = "The user with email: '%s' don't exist";

    public static UserNotFoundException becauseIdDoesNotExist(Long userId) {
        return new UserNotFoundException(String.format(MESSAGE_USER_NOT_EXIST, userId));
    }
}
