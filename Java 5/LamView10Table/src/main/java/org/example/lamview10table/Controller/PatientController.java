package org.example.lamview10table.Controller;

import org.example.lamview10table.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patientsView";
    }
}

