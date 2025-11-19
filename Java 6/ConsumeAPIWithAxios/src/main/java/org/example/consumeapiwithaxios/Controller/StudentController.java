package org.example.consumeapiwithaxios.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
    @RequestMapping("/testAxios")
    public String testAxios() {
        return "test-axios";
    }

}
