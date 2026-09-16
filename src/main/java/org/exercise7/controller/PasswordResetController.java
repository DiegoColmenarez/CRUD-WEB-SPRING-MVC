package org.exercise7.controller;

import org.exercise7.model.service.PasswordResetService;
import org.springframework.web.bind.annotation.GetMapping;

public class PasswordResetController {

    private static final String SESSION_RESET_CODE = "resetCode";
    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @GetMapping
    public String showEmailForm() {
        return "password-reset/enter-email";
    }

}
