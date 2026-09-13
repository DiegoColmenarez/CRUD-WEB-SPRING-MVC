package org.exercise7.model.vo;

import org.exercise7.model.exceptions.InvalidUserPasswordException;

import java.util.regex.Pattern;

public record UserPassword(String value) {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");

    private static void validateFormat(String value){
        if (!PASSWORD_PATTERN.matcher(value).matches()){
            throw InvalidUserPasswordException.becauseFormatIsInvalid();
        }
    }

    private static void validateIsNotEmpty(String value){
        if (value.isEmpty()){
            throw InvalidUserPasswordException.becauseIsEmpty();
        }
    }

    private static void validateIsNotNull(String value){
        if (value == null){
            throw InvalidUserPasswordException.becauseIsNull();
        }
    }


}
