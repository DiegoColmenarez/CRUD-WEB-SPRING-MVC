package org.exercise7.model.vo;

import java.util.regex.Pattern;

public record UserPassword(String value) {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
}
