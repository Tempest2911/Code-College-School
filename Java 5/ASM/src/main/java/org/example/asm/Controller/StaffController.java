package org.example.asm.Controller;

import org.example.asm.Model.Task;
import org.example.asm.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/board")
    public String staffBoard(Model model, Principal principal) {
        String username = "Guest";

        if (principal != null) {
            username = principal.getName();
        }

        // Lấy danh sách task từ DB
        List<Task> tasks = taskRepository.findAll();

        model.addAttribute("username", username);
        model.addAttribute("tasks", tasks); // ✅ Thêm danh sách task vào model

        return "staff-board"; // file staff-board.html
    }
}
