package org.example.lamview10table.Controller;

import org.example.lamview10table.Repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrescriptionController {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @GetMapping("/prescriptions")
    public String getAllPrescriptions(Model model) {
        model.addAttribute("prescriptions", prescriptionRepository.findAll());
        return "prescriptionsView";
    }
}

