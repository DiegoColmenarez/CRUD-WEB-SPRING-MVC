package org.exercise7.model.enums;

public enum TypeUser {
    CLIENTE,
    ADMIN;

    public static boolean isValidValue(final String value) {
        for (final TypeUser role : values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
