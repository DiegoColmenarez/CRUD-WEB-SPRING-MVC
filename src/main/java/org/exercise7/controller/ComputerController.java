package org.exercise7.controller;

import jakarta.validation.Valid;
import org.exercise7.model.entity.Computer;
import org.exercise7.model.enums.Category;
import org.exercise7.model.enums.DiskTechnology;
import org.exercise7.model.enums.RamTechnology;
import org.exercise7.model.exceptions.DomainException;
import org.exercise7.model.service.ComputerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/computers")
public class ComputerController {

    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("ramTechnologies", RamTechnology.values());
        model.addAttribute("diskTechnologies", DiskTechnology.values());
    }

    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        model.addAttribute("computer", new Computer());
        return "computers/form";
    }

    @PostMapping("/save")
    public String processRegistration(
            @Valid @ModelAttribute("computer") Computer computer,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "computers/form";
        }
        try {
            computerService.saveComputer(computer);
        } catch (DomainException e) {
            model.addAttribute("businessError", e.getMessage());
            return "computers/form";
        }
        return "redirect:/computers/list";
    }

    @GetMapping("/list")
    public String listComputers(Model model) {
        model.addAttribute("computers", computerService.findAllComputers());
        return "computers/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteComputer(@PathVariable("id") Long id) {
        computerService.deleteComputer(id);
        return "redirect:/computers/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("computer", computerService.findById(id));
        return "computers/form";
    }

    @GetMapping("/search/brand")
    public String searchByBrand(@RequestParam("brand") String brand, Model model) {
        model.addAttribute("computers", computerService.findByBrand(brand));
        return "computers/list";
    }
}
