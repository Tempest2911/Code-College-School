package org.example.lab2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Bai3 {
    @GetMapping("lab-1/subject/SOF3012")
    public String sayHello() {
        return "hello1";
    }

    @GetMapping("lab-1/subject/SOF3022")

    public String sayBaka1() {
        return "hello2";
    }

    @GetMapping("lab-1/subject/SOF3062")
    public String sayBaka2() {
        return "hello3";
    }

    @GetMapping("lab-1/subject/SOF3063")
    @ResponseBody
    public String sayBaka3() {
        return "không tìm thấy";
    }
}
