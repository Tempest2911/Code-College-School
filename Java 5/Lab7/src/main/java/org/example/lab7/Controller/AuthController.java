package org.example.lab7.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.lab7.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute User user, HttpSession session, Model model) {
        if ("admin".equals(user.getUsername()) && "123".equals(user.getPassword())) {
            session.setAttribute("user", user);
            return "redirect:/products";
        }
        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
