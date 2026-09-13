package org.exercise7.model.vo;

import java.util.regex.Pattern;

public record UserEmail(String value) {

    private static final Pattern PATTERN_EMAIL =
            Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$");



}
