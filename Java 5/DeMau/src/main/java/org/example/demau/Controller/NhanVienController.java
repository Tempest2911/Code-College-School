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

//    @GetMapping("hien-thi")
//    public String list(Model model) {
//        model.addAttribute("nhanVienList", nhanVienService.getAll());
//        NhanVien nhanVien = new NhanVien();
//        nhanVien.setGioiTinh(true);
//        model.addAttribute("nhanVien", nhanVien);
//        model.addAttribute("listChucVu", chucVuRepository.findAll());
//        return "nhanVien";
//    }

    @GetMapping("/hien-thi")
    public String viewHomePage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "hoTen") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String keyword,
            Model model) {

        int pageSize = 5; // mỗi trang 5 dòng

        var pageNhanVien = nhanVienService.getAllPaged(page, pageSize, sortField, sortDir, keyword);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageNhanVien.getTotalPages());
        model.addAttribute("totalItems", pageNhanVien.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

        model.addAttribute("nhanVienList", pageNhanVien.getContent());
        NhanVien nv = new NhanVien();
        nv.setGioiTinh(true);
        model.addAttribute("nhanVien", nv);
        model.addAttribute("listChucVu", chucVuRepository.findAll());

        return "nhanVien";
    }

//    @PostMapping("/add")
//    public String add(
//            @Valid @ModelAttribute("nhanVien") NhanVien nhanVien,
//            BindingResult result,
//            Model model) {
//
//        if (result.hasErrors()) {
//            model.addAttribute("nhanVienList", nhanVienService.getAll());
//            model.addAttribute("listChucVu", chucVuRepository.findAll());
//            return "nhanVien";
//        }
//
//        nhanVienService.save(nhanVien);
//        return "redirect:/nhan-vien/hien-thi";
//    }

    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute("nhanVien") NhanVien nhanVien,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            var pageNhanVien = nhanVienService.getAllPaged(1, 5, "hoTen", "asc", null);
            model.addAttribute("nhanVienList", pageNhanVien.getContent());
            model.addAttribute("listChucVu", chucVuRepository.findAll());
            model.addAttribute("currentPage", 1);
            model.addAttribute("totalPages", pageNhanVien.getTotalPages());
            model.addAttribute("sortField", "hoTen");
            model.addAttribute("sortDir", "asc");
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

    // 🔍 Search theo tên
//    @GetMapping("/search")
//    public String search(@RequestParam("keyword") String keyword, Model model) {
//        model.addAttribute("nhanVienList", nhanVienService.search(keyword));
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("nhanVien", new NhanVien());
//        model.addAttribute("listChucVu", chucVuRepository.findAll());
//        return "nhanVien";
//    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        var pageNhanVien = nhanVienService.getAllPaged(1, 5, "hoTen", "asc", keyword);
        model.addAttribute("nhanVienList", pageNhanVien.getContent());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("listChucVu", chucVuRepository.findAll());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", pageNhanVien.getTotalPages());
        model.addAttribute("sortField", "hoTen");
        model.addAttribute("sortDir", "asc");
        return "nhanVien";
    }


//    @GetMapping("/sort")
//    public String sort(@RequestParam("field") String field,
//                       @RequestParam("dir") String dir,
//                       Model model) {
//        model.addAttribute("nhanVienList", nhanVienService.sortByField(field, dir));
//        model.addAttribute("nhanVien", new NhanVien());
//        model.addAttribute("listChucVu", chucVuRepository.findAll());
//        model.addAttribute("currentSortField", field);
//        model.addAttribute("currentSortDir", dir);
//        return "nhanVien";
//    }

    @GetMapping("/sort")
    public String sort(@RequestParam("field") String field,
                       @RequestParam("dir") String dir,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(required = false) String keyword,
                       Model model) {

        var pageNhanVien = nhanVienService.getAllPaged(page, 5, field, dir, keyword);
        model.addAttribute("nhanVienList", pageNhanVien.getContent());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("listChucVu", chucVuRepository.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageNhanVien.getTotalPages());
        model.addAttribute("sortField", field);
        model.addAttribute("sortDir", dir);
        model.addAttribute("keyword", keyword);
        return "nhanVien";
    }

}
