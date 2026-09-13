package org.exercise7.model.vo;

import org.exercise7.model.enums.TypeUser;
import org.exercise7.model.exceptions.InvalidUserTypeException;

public record UserType(TypeUser value) {

    private static void validateType(String value){
        if (!TypeUser.isValidValue(value)){
            throw InvalidUserTypeException.becauseValueIsInvalid();
        }
    }
}
