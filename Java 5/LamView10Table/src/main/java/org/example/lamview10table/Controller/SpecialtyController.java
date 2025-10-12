package org.example.lamview10table.Controller;

import org.example.lamview10table.Repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpecialtyController {
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @GetMapping("/specialties")
    public String getAllSpecialties(Model model) {
        model.addAttribute("specialties", specialtyRepository.findAll());
        return "specialtiesView";
    }
}

