package org.example.lamview10table.Controller;

import org.example.lamview10table.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    public String getAllDepartments(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "departmentsView";
    }
}

