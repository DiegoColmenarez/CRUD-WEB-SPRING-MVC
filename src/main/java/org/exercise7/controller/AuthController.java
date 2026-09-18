package org.exercise7.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @GetMapping("/")
    public String homeRedirect(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/usuarios/lista";
        }
        return "redirect:/login";
    }
}