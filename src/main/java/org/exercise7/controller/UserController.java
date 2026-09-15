package org.exercise7.controller;

import jakarta.validation.Valid;
import org.exercise7.model.entity.User;
import org.exercise7.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/nuevo")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "users/formulario";
    }

    @PostMapping("/guardar")
    public String processRegistration(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "users/formulario";
        }
        userService.registerUser(user);
        return "redirect:/usuarios/lista";
    }
}
