package org.example.lamview10table.Controller;

import org.example.lamview10table.Repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedicalRecordController {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @GetMapping("/medical-records")
    public String getAllMedicalRecords(Model model) {
        model.addAttribute("medicalRecords", medicalRecordRepository.findAll());
        return "medicalrecordsView";
    }
}

