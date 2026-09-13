package org.exercise7.model.vo;

import org.exercise7.model.exceptions.InvalidUserEmailException;

import java.util.regex.Pattern;

public record UserEmail(String value) {

    private static final Pattern PATTERN_EMAIL =
            Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$");

    private static void validateFormatEmail(String value){
        if (!PATTERN_EMAIL.matcher(value).matches()){
            throw InvalidUserEmailException.becauseFormatIsInvalid();
        }
    }


}
