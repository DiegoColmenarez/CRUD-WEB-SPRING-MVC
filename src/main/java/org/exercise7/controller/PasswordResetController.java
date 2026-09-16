package org.exercise7.controller;

import org.exercise7.model.service.PasswordResetService;

public class PasswordResetController {

    private static final String SESSION_RESET_CODE = "resetCode";
    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }
}
