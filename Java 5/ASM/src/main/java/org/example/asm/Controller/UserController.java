package org.example.asm.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.asm.Model.User;
import org.example.asm.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asm")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ✅ Trang đăng ký
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        // mặc định role = Staff
        if (user.getRole() == null) {
            user.setRole("Staff");
        }
        userRepository.save(user);
        return "redirect:/asm/login";
    }

    // ✅ Trang đăng nhập
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User formUser,
                        HttpSession session,
                        Model model) {
        User user = userRepository.findByUsername(formUser.getUsername());
        if (user != null && user.getPassword().equals(formUser.getPassword())) {
            session.setAttribute("loggedInUser", user);
            // Admin → quản lý task, Staff → xem task của mình
            if ("admin".equals(user.getRole())) {
                return "redirect:/admin/task/list";
            } else {
                return "redirect:/staff/task/list";
            }
        }
        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
        return "login";
    }

    // ✅ Đăng xuất
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/asm/login";
    }
}