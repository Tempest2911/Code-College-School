package org.example.demau.Controller;

;

import jakarta.validation.Valid;
import org.example.demau.Model.NhanVien;
import org.example.demau.Repository.ChucVuRepository;
import org.example.demau.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanvienService;

    @Autowired
    private ChucVuRepository chucvuRepository;

    // ===== HIỂN THỊ ĐƠN GIẢN =====
//    @GetMapping("/hien-thi")
//    public String hienThi(Model model) {
//        model.addAttribute("listNhanVien", nhanvienService.getAll());
//        NhanVien obj = new NhanVien();
//        obj.setGioiTinh(true);
//        model.addAttribute("nhanvien", obj);
//        model.addAttribute("listChucVu", chucvuRepository.findAll());
//        return "nhanvien";
//    }
//
//    // ===== THÊM MỚI (validate cơ bản) =====
//    @PostMapping("/add")
//    public String add(@Valid @ModelAttribute("nhanvien") NhanVien nhanvien, BindingResult result, Model model) {
//
//        if (result.hasErrors()) {
//            nhanvien.setGioiTinh(true);
//            model.addAttribute("listNhanVien", nhanvienService.getAll());
//            model.addAttribute("listChucVu", chucvuRepository.findAll());
//            return "nhanvien";
//        }
//        nhanvienService.save(nhanvien);
//        return "redirect:/nhan-vien/hien-thi";
//    }
//
//    // ===== SỬA =====
//    @GetMapping("/edit/{id}")
//    public String editForm(@PathVariable("id") Integer id, Model model) {
//        model.addAttribute("nhanvien", nhanvienService.getById(id));
//        model.addAttribute("listChucVu", chucvuRepository.findAll());
//        return "EditForm";
//    }
//
//    @PostMapping("/update")
//    public String update(
//            @Valid @ModelAttribute("nhanvien") NhanVien nhanvien,
//            BindingResult result,
//            Model model) {
//
//        if (result.hasErrors()) {
//            model.addAttribute("listChucVu", chucvuRepository.findAll());
//            return "EditForm";
//        }
//
//        nhanvienService.save(nhanvien);
//        return "redirect:/nhan-vien/hien-thi";
//    }
//
//    // ===== XÓA =====
//    @GetMapping("/remove/{id}")
//    public String delete(@PathVariable("id") Integer id) {
//        nhanvienService.delete(id);
//        return "redirect:/nhan-vien/hien-thi";
//    }
//
//    // ===== TÌM KIẾM (đơn giản) =====
//    @GetMapping("/search")
//    public String search(@RequestParam("keyword") String keyword, Model model) {
//        model.addAttribute("listNhanVien", nhanvienService.search(keyword));
//        model.addAttribute("nhanvien", new NhanVien());
//        model.addAttribute("listChucVu", chucvuRepository.findAll());
//        model.addAttribute("keyword", keyword);
//        return "nhanvien";
//    }
//
//    // ===== SẮP XẾP (đơn giản) =====
//    @GetMapping("/sort")
//    public String sort(@RequestParam("field") String field,
//                       @RequestParam("dir") String dir,
//                       Model model) {
//        model.addAttribute("listNhanVien", nhanvienService.sortByField(field, dir));
//        model.addAttribute("nhanvien", new NhanVien());
//        model.addAttribute("listChucVu", chucvuRepository.findAll());
//        return "nhanvien";
//    }

    // =============================================================
    // ============= DƯỚI ĐÂY LÀ CÁC PHIÊN BẢN CÓ PHÂN TRANG =========
    // =============================================================


    // --- HIỂN THỊ PHÂN TRANG ---
    @GetMapping("/hien-thi")
    public String viewHomePage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "hoTen") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String keyword,
            Model model) {

        int pageSize = 5;
        var pageNhanVien = nhanvienService.getAllPaged(page, pageSize, sortField, sortDir, keyword);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageNhanVien.getTotalPages());
        model.addAttribute("totalItems", pageNhanVien.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

        model.addAttribute("listNhanVien", pageNhanVien.getContent());
        NhanVien obj = new NhanVien();
        model.addAttribute("nhanvien", obj);
        model.addAttribute("listChucVu", chucvuRepository.findAll());
        return "nhanvien";
    }

    // --- THÊM MỚI (CÓ VALIDATE + PHÂN TRANG) ---
    @PostMapping("/add")
    public String addPaged(
            @Valid @ModelAttribute("nhanvien") NhanVien nhanvien,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            var pageNhanVien = nhanvienService.getAllPaged(1, 5, "hoTen", "asc", null);
            model.addAttribute("listNhanVien", pageNhanVien.getContent());
            model.addAttribute("listChucVu", chucvuRepository.findAll());
            model.addAttribute("currentPage", 1);
            model.addAttribute("totalPages", pageNhanVien.getTotalPages());
            model.addAttribute("sortField", "hoTen");
            model.addAttribute("sortDir", "asc");
            return "nhanvien";
        }

        nhanvienService.save(nhanvien);
        return "redirect:/nhanvien/hien-thi";
    }

    // --- TÌM KIẾM (CÓ PHÂN TRANG) ---
    @GetMapping("/search")
    public String searchPaged(@RequestParam("keyword") String keyword, Model model) {
        var pageNhanVien = nhanvienService.getAllPaged(1, 5, "hoTen", "asc", keyword);
        model.addAttribute("listNhanVien", pageNhanVien.getContent());
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("listChucVu", chucvuRepository.findAll());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", pageNhanVien.getTotalPages());
        model.addAttribute("sortField", "hoTen");
        model.addAttribute("sortDir", "asc");
        return "nhanvien";
    }

    // --- SẮP XẾP (CÓ PHÂN TRANG) ---
    @GetMapping("/sort")
    public String sortPaged(@RequestParam("field") String field,
                       @RequestParam("dir") String dir,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(required = false) String keyword,
                       Model model) {

        var pageNhanVien = nhanvienService.getAllPaged(page, 5, field, dir, keyword);
        model.addAttribute("listNhanVien", pageNhanVien.getContent());
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("listChucVu", chucvuRepository.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageNhanVien.getTotalPages());
        model.addAttribute("sortField", field);
        model.addAttribute("sortDir", dir);
        model.addAttribute("keyword", keyword);
        return "nhanvien";
    }

}
