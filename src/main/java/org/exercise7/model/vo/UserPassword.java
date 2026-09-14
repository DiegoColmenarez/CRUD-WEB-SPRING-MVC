package org.exercise7.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.exercise7.model.exceptions.InvalidUserPasswordException;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class UserPassword {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");

    @Column(name = "password", columnDefinition = "TEXT")
    private String value;

    protected UserPassword() {}

    public UserPassword(String value) {
        validateNotNull(value);
        validateNotEmpty(value);
        validateFormat(value);
        this.value = value;
    }

    private static void validateFormat(String value) {
        if (!PASSWORD_PATTERN.matcher(value).matches()) {
            throw InvalidUserPasswordException.becauseFormatIsInvalid();
        }
    }

    private static void validateNotEmpty(String value) {
        if (value.isEmpty()) {
            throw InvalidUserPasswordException.becauseIsEmpty();
        }
    }

    private static void validateNotNull(String value) {
        if (value == null) {
            throw InvalidUserPasswordException.becauseIsNull();
        }
    }

    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPassword)) return false;
        return Objects.equals(value, ((UserPassword) o).value);
    }

    @Override
    public int hashCode() { return Objects.hash(value); }

    @Override
    public String toString() { return "********"; }
}