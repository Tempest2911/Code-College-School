package org.example.hellospringboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("subject", "Spring Boot MVC");
        model.addAttribute("author", "Nguyễn Văn A");
        model.addAttribute("year", 2025);
        return "demo/hello";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<String> lists = List.of("Nguyễn Văn A", "Trần Thị B", "Lê Văn C");
        model.addAttribute("students", lists);
        return "demo/hello";
    }

}
