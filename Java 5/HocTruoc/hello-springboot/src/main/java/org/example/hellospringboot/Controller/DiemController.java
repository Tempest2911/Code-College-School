package org.example.hellospringboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/diem")
public class DiemController {
    @GetMapping("/ASM/{gd1}/{giaiDoan2}")
    @ResponseBody
    public String TinhASM(@PathVariable float gd1,
                          @PathVariable(name = "giaiDoan2") float gd2){

        double ketQua = (gd1*0.1) + (gd2*0.1);

        return "ket qua = " + ketQua;
    }
}
