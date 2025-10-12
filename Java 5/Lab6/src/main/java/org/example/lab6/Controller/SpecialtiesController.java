package org.example.lab6.Controller;


import org.example.lab6.Model.Department;
import org.example.lab6.Repository.SpecialtiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/specialties")
public class SpecialtiesController {

    @Autowired
    private SpecialtiesRepository specialtiesRepository;

    @GetMapping
    public String list(
            Model model,
            @RequestParam(value = "startCreatedAt", required = false) String startCreatedAt,
            @RequestParam(value = "endCreatedAt", required = false) String endCreatedAt) {
        if (startCreatedAt != null && endCreatedAt != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(startCreatedAt, formatter);
            LocalDate endDate = LocalDate.parse(endCreatedAt, formatter);
            Instant start = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
            Instant end = endDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().minusMillis(1);
            model.addAttribute("specialties", specialtiesRepository.findByCreatedAtBetween(start, end));
        } else {
            model.addAttribute("specialties", specialtiesRepository.findAll());
        }
        return "hello";
    }

    @GetMapping("/{id}")
    public String showThongTinChitiet(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("chitiet", specialtiesRepository.findById(id).orElse(null));
        return "thongtinchitiet";
    }

    @GetMapping("/departments/{departmentId}")
    public String listByDepartment(@PathVariable("departmentId") Department departmentId, Model model) {
        model.addAttribute("specialties", specialtiesRepository.findByDepartmentID(departmentId));
        return "hello";
    }

}
