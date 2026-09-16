package org.exercise7.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.exercise7.model.dto.PasswordResetRequest;
import org.exercise7.model.entity.User;
import org.exercise7.model.exceptions.DomainException;
import org.exercise7.model.service.PasswordResetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/password-reset")
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

    @GetMapping("/verify")
    public String showCodeForm() {
        return "password-reset/enter-code";
    }

    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam("code") String code,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        if (code == null || !code.matches("\\d{6}")) {
            redirectAttributes.addFlashAttribute("errorMessage", "Code must be 6 digits");
            return "redirect:/password-reset/verify";
        }

        Optional<User> user = passwordResetService.validateCode(code);
        if (user.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid or expired code");
            return "redirect:/password-reset/verify";
        }

        session.setAttribute(SESSION_RESET_CODE, code);
        return "redirect:/password-reset/new-password";
    }

    @GetMapping("/new-password")
    public String showNewPasswordForm(HttpSession session, Model model) {
        String code = (String) session.getAttribute(SESSION_RESET_CODE);
        if (code == null) {
            return "redirect:/password-reset";
        }

        Optional<User> user = passwordResetService.validateCode(code);
        if (user.isEmpty()) {
            session.removeAttribute(SESSION_RESET_CODE);
            return "redirect:/password-reset";
        }

        if (!model.containsAttribute("passwordResetRequest")) {
            model.addAttribute("passwordResetRequest", new PasswordResetRequest());
        }
        return "password-reset/new-password";
    }

    @PostMapping("/update-password")
    public String updatePassword(@Valid @ModelAttribute("passwordResetRequest") PasswordResetRequest request,
                                 BindingResult result,
                                 HttpSession session,
                                 Model model) {

        String code = (String) session.getAttribute(SESSION_RESET_CODE);
        if (code == null) {
            return "redirect:/password-reset";
        }
        if (request.getPassword() != null
                && !request.getPassword().equals(request.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "mismatch", "Passwords do not match");
        }
        if (result.hasErrors()) {
            return "password-reset/new-password";
        }

        try {
            passwordResetService.resetPassword(code, request.getPassword());
            session.removeAttribute(SESSION_RESET_CODE);
            session.removeAttribute("resetEmail");
            return "redirect:/login?resetSuccess";
        } catch (DomainException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "password-reset/new-password";
        }
    }
}
