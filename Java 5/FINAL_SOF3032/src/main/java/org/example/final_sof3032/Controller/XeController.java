package org.example.final_sof3032.Controller;

import org.example.final_sof3032.Model.Xe;
import org.example.final_sof3032.Repository.LoaiXeRepository;
import org.example.final_sof3032.Repository.XeRepository;
import org.example.final_sof3032.Service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/xe")
public class XeController {

    @Autowired
    XeService xeService;

    @Autowired
    XeRepository xeRepository;

    @Autowired
    LoaiXeRepository loaiXeRepository;

    @GetMapping("/hien-thi")
    public String viewHomePage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "tenXe") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String keyword,
            Model model) {

        int pageSize = 5;
        var pageXe = xeService.getAllPaged(page, pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageXe.getTotalPages());
        model.addAttribute("totalItems", pageXe.getTotalElements());
        model.addAttribute("listXe", pageXe.getContent());
        Xe obj = new Xe();
        model.addAttribute("xe", obj);
        model.addAttribute("listLoaiXe", loaiXeRepository.findAll());
        return "xe";
    }

    @GetMapping("/update/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("xe", xeService.getById(id));
        model.addAttribute("listLoaiXe", loaiXeRepository.findAll());
        return "EditForm";
    }

    @PostMapping("/update")
    public String update(
           @ModelAttribute("xe") Xe xe) {
        xeService.save(xe);
        return "redirect:/xe/hien-thi";
    }
}
