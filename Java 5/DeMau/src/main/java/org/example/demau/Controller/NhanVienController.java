package org.example.demau.Controller;

import jakarta.validation.Valid;
import org.example.demau.Model.NhanVien;
import org.example.demau.Repository.NhanVienRepository;
import org.example.demau.Service.NhanVienService;
import org.example.demau.Repository.ChucVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private ChucVuRepository chucVuRepository;

    @GetMapping("hien-thi")
    public String list(Model model) {
        model.addAttribute("nhanVienList", nhanVienService.getAll());
        NhanVien nhanVien = new NhanVien();
        nhanVien.setGioiTinh(true);
        model.addAttribute("nhanVien", nhanVien);
        model.addAttribute("listChucVu", chucVuRepository.findAll());
        return "nhanVien";
    }

    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute("nhanVien") NhanVien nhanVien,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("nhanVienList", nhanVienService.getAll());
            model.addAttribute("listChucVu", chucVuRepository.findAll());
            return "nhanVien";
        }

        nhanVienService.save(nhanVien);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("nhanVien", nhanVienService.getById(id));
        model.addAttribute("listChucVu", chucVuRepository.findAll());
        return "EditForm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("nhanVien") NhanVien nhanVien) {
        nhanVienService.save(nhanVien);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/remove/{id}")
    public String delete(@PathVariable("id") Integer id) {
        nhanVienService.delete(id);
        return "redirect:/nhan-vien/hien-thi";
    }
}
