package org.example.lab2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Bai2 {
    @GetMapping("lab-1/qua-mon")
    @ResponseBody
    public String mayTinh1(@RequestParam(name = "soBuoiNghi") double a, @RequestParam(name = "diemFinal") double b) {
        if (a<4 && b>=5) {
            return "PASS";
        }else{
            return "FAIL";
        }
    }
}
