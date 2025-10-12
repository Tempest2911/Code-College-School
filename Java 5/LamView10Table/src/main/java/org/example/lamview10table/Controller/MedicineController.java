package org.example.lamview10table.Controller;

import org.example.lamview10table.Repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedicineController {
    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping("/medicines")
    public String getAllMedicines(Model model) {
        model.addAttribute("medicines", medicineRepository.findAll());
        return "medicinesView";
    }
}

