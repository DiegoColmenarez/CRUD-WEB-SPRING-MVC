package org.exercise7.model.vo;

import org.exercise7.model.exceptions.InvalidUserNameException;

import java.util.Objects;
import java.util.regex.Pattern;

public record UserName(String value) {

    private static final Pattern NAME_PATTERN =
            Pattern.compile("^[\\p{L} \\-']+$");

    private static void validateNameFormat(String value) {
        if (!NAME_PATTERN.matcher(value).matches()) {
            throw InvalidUserNameException.becauseContainsInvalidCharacters();
        }
    }

    private  static void validateNameNotEmpty(String value){
        if (value.isEmpty()){
            throw InvalidUserNameException.becauseIsEmpty();
        }
    }


}