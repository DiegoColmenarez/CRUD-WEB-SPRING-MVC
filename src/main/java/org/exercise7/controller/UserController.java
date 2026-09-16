package org.exercise7.controller;

import jakarta.validation.Valid;
import org.exercise7.model.entity.User;
import org.exercise7.model.enums.TypeUser;
import org.exercise7.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        model.addAttribute("roles", TypeUser.values());
        return "users/formulario";
    }

    @PostMapping("/guardar")
    public String processRegistration(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", TypeUser.values());
            return "users/formulario";
        }
        userService.saveUser(user);
        return "redirect:/usuarios/lista";
    }

    @GetMapping("/lista")
    public String listUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users/lista";
    }

    @PostMapping("/eliminar/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/usuarios/lista";
    }

    @GetMapping("/tipo")
    public String listUsersByType(@RequestParam("tipo") TypeUser type, Model model) {
        List<User> users = userService.findUsersByType(type);
        model.addAttribute("users", users);
        return "users/lista";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roles", TypeUser.values());
        return "users/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String updateUser(@PathVariable Long id,
                            @Valid @ModelAttribute("user") User user,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            model.addAttribute("roles", TypeUser.values());
            return "users/formulario";
        }
        userService.updateUserBasicInfo(id, user.getName(), user.getLastName(), user.getEmail());
        return "redirect:/usuarios/lista";
    }

    @GetMapping("/buscar/apellido")
    public String findByLastName(@RequestParam("apellido") String apellido, Model model) {
        model.addAttribute("users", userService.findUsersByLastName(apellido));
        return "users/lista";
    }

    @GetMapping("/buscar/nombre")
    public String findByName(@RequestParam("nombre") String nombre, Model model) {
        model.addAttribute("users", userService.findUsersByName(nombre));
        return "users/lista";
    }


}