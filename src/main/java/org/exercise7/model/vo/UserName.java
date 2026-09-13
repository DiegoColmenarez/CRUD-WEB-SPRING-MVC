package org.exercise7.model.vo;

import java.util.regex.Pattern;

public record UserName(String value) {

    private static final Pattern NAME_PATTERN =
            Pattern.compile("^[\\p{L} \\-']+$");
}
