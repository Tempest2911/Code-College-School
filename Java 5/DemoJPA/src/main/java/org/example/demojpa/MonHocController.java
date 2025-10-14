package org.example.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MonHocController {

    @Autowired
    private MonHocRepository monHocRepository;

    @GetMapping("/mon-hoc")
    public String xemDanhSach(Model model) {
        List<MonHoc> danhSachMonHoc = monHocRepository.findAll();
        model.addAttribute("listMonHoc", danhSachMonHoc);
        return "mon-hoc";
    }

    @GetMapping("/mon-hoc/them")
    public String themMonHoc(Model model) {
        MonHoc monHoc = new MonHoc();
        monHoc.setMaMonHoc("soft3071");
        monHocRepository.save(monHoc);
        return "redirect:/mon-hoc";
    }

    @GetMapping("/mon-hoc/xoa/{id}")
    public String xoaMonHoc(@PathVariable("id") Long idMonHoc) {
        monHocRepository.deleteById(idMonHoc);
        return "redirect:/mon-hoc";
    }

    @GetMapping("/mon-hoc/sua/{id}")
    public String suaMonHoc(@PathVariable("id") Long idMonHoc, Model model) {
        MonHoc monHoc = monHocRepository.findById(idMonHoc).orElse(null);
        if (monHoc != null) {
            monHoc.setTenMonHoc("Môn học đã được cập nhật");
            monHocRepository.save(monHoc);
        }
        return "redirect:/mon-hoc";
    }

}
