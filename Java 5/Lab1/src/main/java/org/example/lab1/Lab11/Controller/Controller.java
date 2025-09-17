package org.example.lab1.Lab11.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {

@GetMapping("/hello")
@ResponseBody
public String hello() {
    return "Hello, Nguyễn Duy Phong";
}

@GetMapping("/about")
@ResponseBody
public String about() {

    return "TH03089 Nguyễn Duy Phong Game";
}

    @GetMapping("/syllabus/sof3022")
    public String sayHello() {
        return "hello1";
    }

    @GetMapping("/syllabus/sof3012")
    public String sayBaka() {
        return "hello2";
    }
}
