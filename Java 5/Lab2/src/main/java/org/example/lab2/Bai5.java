package org.example.lab2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Bai5 {
    private static final List<Love> lovers = new ArrayList<>();

    @GetMapping("lab-1/nguoi-yeu")
    public String showForm(Model model) {
        model.addAttribute("lovers", lovers);
        return "ny";
    }

    @PostMapping("lab-1/nguoi-yeu")
    public String addLover(@RequestParam String nickName,
                           @RequestParam int namSinh,
                           Model model) {
        lovers.add(new Love(nickName, namSinh));
        model.addAttribute("lovers", lovers);
        return "ny";
    }
}
