package org.example.demau3.Controller;

;

import jakarta.validation.Valid;

import org.example.demau3.Model.SanPham;
import org.example.demau3.Repository.LoaiSanPhamRepository;
import org.example.demau3.Repository.SanPhamRepository;
import org.example.demau3.Service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sanpham")
public class SanPhamController {

    @Autowired
    private SanPhamRepository sanphamRepository;


    @Autowired
    private SanPhamService sanphamService;

    @Autowired
    private LoaiSanPhamRepository loaisanphamRepository;

//    // ===== HIỂN THỊ ĐƠN GIẢN =====
//    @GetMapping("/hien-thi")
//    public String hienThi(Model model) {
//        model.addAttribute("listSanPham", sanphamRepository.findAll());
//        List<SanPham> list = sanphamRepository.findAll();
//        System.out.println("Số lượng sản phẩm: " + list.size());
//        model.addAttribute("listSanPham", list);
//
//        SanPham obj = new SanPham();
//        //obj.setGioiTinh(true);
//        model.addAttribute("sanpham", obj);
//        model.addAttribute("listLoaiSanPham", loaisanphamRepository.findAll());
//        return "sanpham";
//    }
//
//    // ===== THÊM MỚI (validate cơ bản) =====
//    @PostMapping("/add")
//    public String add(@Valid @ModelAttribute("sanpham") SanPham sanpham,
//                      BindingResult result,
//                      Model model) {
//        if (result.hasErrors()) {
//            // sanpham.setGioiTinh(true);
//            model.addAttribute("listSanPham", sanphamRepository.findAll());
//            model.addAttribute("listLoaiSanPham", loaisanphamRepository.findAll());
//            return "sanpham";
//        }
//        sanphamRepository.save(sanpham);
//        return "redirect:/sanpham/hien-thi";
//    }
//
//    // ===== SỬA =====
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("sanpham", sanphamService.getById(id));
        model.addAttribute("listLoaiSanPham", loaisanphamRepository.findAll());
        return "EditForm";
    }
//
    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("sanpham") SanPham sanpham,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("listLoaiSanPham", loaisanphamRepository.findAll());
            return "EditForm";
        }

        sanphamRepository.save(sanpham);
        return "redirect:/sanpham/hien-thi";
    }

    // ===== XÓA =====
    @GetMapping("/remove/{id}")
    public String delete(@PathVariable("id") Integer id) {
        sanphamService.delete(id);
        return "redirect:/sanpham/hien-thi";
    }
//
//    // ===== TÌM KIẾM (đơn giản) =====
//    @GetMapping("/search")
//    public String search(@RequestParam("keyword") String keyword, Model model) {
//        model.addAttribute("listSanPham", sanphamRepository.searchBytenSanPham(keyword));
//        model.addAttribute("sanpham", new SanPham());
//        model.addAttribute("listLoaiSanPham", loaisanphamRepository.findAll());
//        model.addAttribute("keyword", keyword);
//        return "sanpham";
//    }
//
//    // ===== SẮP XẾP (đơn giản) =====
//    @GetMapping("/sort")
//    public String sort(@RequestParam("field") String field,
//                       @RequestParam("dir") String dir,
//                       Model model) {
//        Sort sort = dir.equalsIgnoreCase("asc")
//                ? Sort.by(Sort.Direction.ASC, field)
//                : Sort.by(Sort.Direction.DESC, field);
//
//        model.addAttribute("listSanPham", sanphamRepository.findAll(sort));
//        model.addAttribute("sanpham", new SanPham());
//        model.addAttribute("listLoaiSanPham", loaisanphamRepository.findAll());
//        return "sanpham";
//    }


    // --- HIỂN THỊ PHÂN TRANG ---
    @GetMapping("/hien-thi")
    public String viewHomePage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "tenSanPham") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String keyword,
            Model model) {

        int pageSize = 5;
        var pageSanPham = sanphamService.getAllPaged(page, pageSize, sortField, sortDir, keyword);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageSanPham.getTotalPages());
        model.addAttribute("totalItems", pageSanPham.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

        model.addAttribute("listSanPham", pageSanPham.getContent());
        SanPham obj = new SanPham();
        model.addAttribute("sanpham", obj);
        model.addAttribute("listLoaiSanPham", loaisanphamRepository.findAll());
        return "sanpham";
    }

    // --- THÊM MỚI (CÓ VALIDATE + PHÂN TRANG) ---
    @PostMapping("/add")
    public String addPaged(
            @Valid @ModelAttribute("sanpham") SanPham sanpham,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            var pageSanPham = sanphamService.getAllPaged(1, 5, "tenSanPham", "asc", null);
            model.addAttribute("listSanPham", pageSanPham.getContent());
            model.addAttribute("listLoaiSanPham", loaisanphamRepository.findAll());
            model.addAttribute("currentPage", 1);
            model.addAttribute("totalPages", pageSanPham.getTotalPages());
            model.addAttribute("sortField", "tenSanPham");
            model.addAttribute("sortDir", "asc");
            return "sanpham";
        }

        sanphamService.save(sanpham);
        return "redirect:/sanpham/hien-thi";
    }

    // --- TÌM KIẾM (CÓ PHÂN TRANG) ---
    @GetMapping("/search")
    public String searchPaged(@RequestParam("keyword") String keyword, Model model) {
        var pageSanPham = sanphamService.getAllPaged(1, 5, "tenSanPham", "asc", keyword);
        model.addAttribute("listSanPham", pageSanPham.getContent());
        model.addAttribute("sanpham", new SanPham());
        model.addAttribute("listLoaiSanPham", loaisanphamRepository.findAll());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", pageSanPham.getTotalPages());
        model.addAttribute("sortField", "tenSanPham");
        model.addAttribute("sortDir", "asc");
        return "sanpham";
    }

    // --- SẮP XẾP (CÓ PHÂN TRANG) ---
    @GetMapping("/sort")
    public String sortPaged(@RequestParam("field") String field,
                       @RequestParam("dir") String dir,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(required = false) String keyword,
                       Model model) {

        var pageSanPham = sanphamService.getAllPaged(page, 5, field, dir, keyword);
        model.addAttribute("listSanPham", pageSanPham.getContent());
        model.addAttribute("sanpham", new SanPham());
        model.addAttribute("listLoaiSanPham", loaisanphamRepository.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageSanPham.getTotalPages());
        model.addAttribute("sortField", field);
        model.addAttribute("sortDir", dir);
        model.addAttribute("keyword", keyword);
        return "sanpham";
    }

}
