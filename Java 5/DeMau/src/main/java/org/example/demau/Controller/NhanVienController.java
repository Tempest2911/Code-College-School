package org.example.demau.Controller;

import org.example.demau.Model.NhanVien;
import org.example.demau.Repository.ChucVuRepository;
import org.example.demau.Repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private ChucVuRepository chucVuRepository;

    // Danh sách + form thêm mới
    @GetMapping
    public String list(Model model) {
        model.addAttribute("nhanVienList", nhanVienRepository.findAll());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("listChucVu", chucVuRepository.findAll());
        return "nhanVien";
    }

    // Lưu thêm mới
    @PostMapping("/add")
    public String add(@ModelAttribute("nhanVien") NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
        return "redirect:/nhan-vien";
    }

    // Trang sửa riêng
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        NhanVien nv = nhanVienRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
        model.addAttribute("nhanVien", nv);
        model.addAttribute("listChucVu", chucVuRepository.findAll());
        return "EditForm";
    }

    // Lưu cập nhật
    @PostMapping("/update")
    public String update(@ModelAttribute("nhanVien") NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
        return "redirect:/nhan-vien";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        nhanVienRepository.deleteById(id);
        return "redirect:/nhan-vien";
    }
}
