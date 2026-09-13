package org.exercise7.model.vo;

import org.exercise7.model.exceptions.InvalidUserIdException;

public record UserId(int value) {

    private static void validateInt(int value){
        if (value <= 0){
            throw InvalidUserIdException.becauseIdIsInvalid();
        }
    }

    public UserId{
        validateInt(value);
    }
}
