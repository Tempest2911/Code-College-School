package org.example.buoi4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HienThiController {

    @GetMapping("/mon-hoc")
    public String hienThiDanhSachMonHoc(Model model){
        String[] monHocs = {"SOF3012", "SOF3022", "SOF3062"};
        model.addAttribute("danhSachMonHoc", monHocs);
        return "mon-hoc";
    }
}
