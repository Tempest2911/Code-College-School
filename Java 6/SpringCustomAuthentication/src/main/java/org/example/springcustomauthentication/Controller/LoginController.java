package org.example.springcustomauthentication.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginController {
    @RequestMapping("/auth/login")
    public String loginPage(Model model) {
        model.addAttribute("message", "Đăng nhập");
        return "login";
    }

    @RequestMapping("/auth/{action}")
    public String login(@PathVariable("action") String action, Model model) {
        return switch (action) {
            case "form" -> {
                model.addAttribute("message", "Đăng nhập");
                yield "login";
            }
            case "success" -> {
                model.addAttribute("message", "Đăng nhập thành công");
                yield "success";
            }
            case "fail" -> {
                model.addAttribute("message", "Đăng nhập thất bại");
                yield "failure";
            }
            case "exit" -> {
                model.addAttribute("message", "Đăng xuất thành công");
                yield "login";
            }
            default -> "/auth/login";
        };
    }

}
