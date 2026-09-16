package org.exercise7.controller;

import jakarta.servlet.http.HttpSession;
import org.exercise7.model.service.PasswordResetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/send-code")
    public String sendCode(@RequestParam("email") String email,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        if (email == null || !email.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$")) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid email format");
            return "redirect:/password-reset";
        }

        passwordResetService.requestResetCode(email);
        session.setAttribute("resetEmail", email);
        redirectAttributes.addFlashAttribute("successMessage",
                "If the email is registered, you will receive a reset code");
        return "redirect:/password-reset/verify";
    }
}
