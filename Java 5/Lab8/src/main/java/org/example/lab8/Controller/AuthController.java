package org.example.lab8.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.lab8.Model.User;
import org.example.lab8.Repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepo;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        if (username.equals("admin") && password.equals("admin")) {
            session.setAttribute("isAdmin", true);
            session.setAttribute("user", new User(null, "admin", "admin", "Quản trị viên"));
            return "redirect:/admin";
        }

        return userRepo.findByUsernameAndPassword(username, password)
                .map(u -> {
                    session.setAttribute("user", u);
                    return "redirect:/post";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Sai username hoặc mật khẩu!");
                    return "login";
                });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
