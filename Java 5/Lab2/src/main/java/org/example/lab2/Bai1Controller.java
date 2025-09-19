package org.example.lab2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Bai1Controller {
    @GetMapping("lab-1/gpa")
    @ResponseBody
    public String mayTinh1(@RequestParam(name = "lab") double a,
                           @RequestParam(name = "quiz") double b,
                           @RequestParam(name = "assignment") double c) {
        double d = (a*0.26) + (b*0.14) + (c*0.2);
        if (a < 0 || a > 10 || b < 0 || b > 10 || c < 0 || c > 10) {
            return "Diem khong hop le";
        }
        return "ketqua = " +d;
    }
}
