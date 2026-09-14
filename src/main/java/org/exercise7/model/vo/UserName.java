package org.exercise7.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.exercise7.model.exceptions.InvalidUserNameException;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class UserName {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[\\p{L} \\-']+$");

    @Column(name = "nombre", length = 100)
    private String value;

    protected UserName() {}

    public UserName(String value) {
        validateNotNull(value);
        String normalized = value.trim().toUpperCase();
        if (normalized.isEmpty()) {
            throw InvalidUserNameException.becauseIsEmpty();
        }
        if (!NAME_PATTERN.matcher(normalized).matches()) {
            throw InvalidUserNameException.becauseContainsInvalidCharacters();
        }
        this.value = normalized;
    }

    private static void validateNotNull(String value) {
        if (value == null) {
            throw InvalidUserNameException.becauseIsNull();
        }
    }

    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserName)) return false;
        return Objects.equals(value, ((UserName) o).value);
    }

    @Override
    public int hashCode() { return Objects.hash(value); }

    @Override
    public String toString() { return value; }
}