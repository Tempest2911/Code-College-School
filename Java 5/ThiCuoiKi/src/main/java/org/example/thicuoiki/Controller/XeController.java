package org.example.thicuoiki.Controller;

;

import jakarta.validation.Valid;
import org.example.thicuoiki.Model.Xe;
import org.example.thicuoiki.Repository.LoaiXeRepository;
import org.example.thicuoiki.Service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/xe")
public class XeController {

    @Autowired
    private XeService xeService;

    @Autowired
    private LoaiXeRepository loaixeRepository;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("listXe", xeService.getAll());
        Xe obj = new Xe();
        model.addAttribute("xe", obj);
        model.addAttribute("listLoaiXe", loaixeRepository.findAll());
        return "xe";
    }
//
//    @PostMapping("/add")
//    public String add(@Valid @ModelAttribute("xe") Xe xe,
//                      BindingResult result,
//                      Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("listXe", xeService.getAll());
//            model.addAttribute("listLoaiXe", loaixeRepository.findAll());
//            return "xe";
//        }
//        xeService.save(xe);
//        return "redirect:/xe/hien-thi";
//    }

    @GetMapping("/update/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("xe", xeService.getById(id));
        model.addAttribute("listLoaiXe", loaixeRepository.findAll());
        return "EditForm";
    }

    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("xe") Xe xe,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("listLoaiXe", loaixeRepository.findAll());
            return "EditForm";
        }

        xeService.save(xe);
        return "redirect:/xe/hien-thi";
    }

    @GetMapping("/remove/{id}")
    public String delete(@PathVariable("id") Integer id) {
        xeService.delete(id);
        return "redirect:/xe/hien-thi";
    }

//    @GetMapping("/search")
//    public String search(@RequestParam("keyword") String keyword, Model model) {
//        model.addAttribute("listXe", xeService.search(keyword));
//        model.addAttribute("xe", new Xe());
//        model.addAttribute("listLoaiXe", loaixeRepository.findAll());
//        model.addAttribute("keyword", keyword);
//        return "xe";
//    }
//
//    @GetMapping("/sort")
//    public String sort(@RequestParam("field") String field,
//                       @RequestParam("dir") String dir,
//                       Model model) {
//        model.addAttribute("listXe", xeService.sortByField(field, dir));
//        model.addAttribute("xe", new Xe());
//        model.addAttribute("listLoaiXe", loaixeRepository.findAll());
//        return "xe";
//    }

    // --- HIỂN THỊ PHÂN TRANG ---
    @GetMapping("/hien-thi")
    public String viewHomePage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "tenXe") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String keyword,
            Model model) {

        int pageSize = 5;
        var pageXe = xeService.getAllPaged(page, pageSize, sortField, sortDir, keyword);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageXe.getTotalPages());
        model.addAttribute("totalItems", pageXe.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

        model.addAttribute("listXe", pageXe.getContent());
        Xe obj = new Xe();
        model.addAttribute("xe", obj);
        model.addAttribute("listLoaiXe", loaixeRepository.findAll());
        return "xe";
    }

    @PostMapping("/add")
    public String addPaged(
            @Valid @ModelAttribute("xe") Xe xe,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            var pageXe = xeService.getAllPaged(1, 5, "tenXe", "asc", null);
            model.addAttribute("listXe", pageXe.getContent());
            model.addAttribute("listLoaiXe", loaixeRepository.findAll());
            model.addAttribute("currentPage", 1);
            model.addAttribute("totalPages", pageXe.getTotalPages());
            model.addAttribute("sortField", "tenXe");
            model.addAttribute("sortDir", "asc");
            return "xe";
        }

        xeService.save(xe);
        return "redirect:/xe/hien-thi";
    }

    @GetMapping("/search")
    public String searchPaged(@RequestParam("keyword") String keyword, Model model) {
        var pageXe = xeService.getAllPaged(1, 5, "tenXe", "asc", keyword);
        model.addAttribute("listXe", pageXe.getContent());
        model.addAttribute("xe", new Xe());
        model.addAttribute("listLoaiXe", loaixeRepository.findAll());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", pageXe.getTotalPages());
        model.addAttribute("sortField", "tenXe");
        model.addAttribute("sortDir", "asc");
        return "xe";
    }

    @GetMapping("/sort")
    public String sortPaged(@RequestParam("field") String field,
                       @RequestParam("dir") String dir,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(required = false) String keyword,
                       Model model) {

        var pageXe = xeService.getAllPaged(page, 5, field, dir, keyword);
        model.addAttribute("listXe", pageXe.getContent());
        model.addAttribute("xe", new Xe());
        model.addAttribute("listLoaiXe", loaixeRepository.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageXe.getTotalPages());
        model.addAttribute("sortField", field);
        model.addAttribute("sortDir", dir);
        model.addAttribute("keyword", keyword);
        return "xe";
    }

}
