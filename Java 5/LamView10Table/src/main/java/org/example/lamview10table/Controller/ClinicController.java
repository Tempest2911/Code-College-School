package org.example.lamview10table.Controller;

import org.example.lamview10table.Repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClinicController {
    @Autowired
    private ClinicRepository clinicRepository;

    @GetMapping("/clinics")
    public String getAllClinics(Model model) {
        model.addAttribute("clinics", clinicRepository.findAll());
        return "clinicsView";
    }
}

