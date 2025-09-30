package org.example.asm.Controller;

import org.example.asm.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/board")
    public String staffBoard(Model model, Principal principal) {
        String username = "Guest";

        if (principal != null) {
            username = principal.getName();
        }

        model.addAttribute("username", username);
        return "staff-board";
    }
}

