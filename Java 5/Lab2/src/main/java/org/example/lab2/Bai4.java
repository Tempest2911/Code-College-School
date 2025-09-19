package org.example.lab2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

    @Controller
public class Bai4 {

    @GetMapping("lab-1/hinh-chu-nhat")
    public String showForm() {
        return "hcn";
    }

    @PostMapping("lab-1/hinh-chu-nhat")
    public String calculate(
            @RequestParam int chieuDai,
            @RequestParam int chieuRong,
            @RequestParam String action,
            Model model) {

        if ("dienTich".equals(action)) {
            model.addAttribute("message", "Diện tích: " + (chieuDai * chieuRong));
        } else if ("chuVi".equals(action)) {
            model.addAttribute("message", "Chu vi: " + ((chieuDai + chieuRong) * 2));
        }
        model.addAttribute("chieuDai", chieuDai);
        model.addAttribute("chieuRong", chieuRong);
        return "hcn";
    }

}
