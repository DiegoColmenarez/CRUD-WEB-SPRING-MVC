package org.exercise7.controller;

import org.exercise7.model.enums.Category;
import org.exercise7.model.enums.DiskTechnology;
import org.exercise7.model.enums.RamTechnology;
import org.exercise7.model.service.ComputerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
