package org.example.hellospringboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("subject", "Spring Boot MVC");
        return "demo/hello"; // This refers to hello.html in src/main/resources/templates/
    }
}
