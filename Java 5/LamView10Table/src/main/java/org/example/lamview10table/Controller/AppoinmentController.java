package org.example.lamview10table.Controller;

import org.example.lamview10table.Model.Specialty;
import org.example.lamview10table.Repository.AppoinmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
public class AppoinmentController {

    @Autowired
        private AppoinmentRepository appoinmentRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("appointments", appoinmentRepository.findAll());
        return "appointmentsView";
    }

}
