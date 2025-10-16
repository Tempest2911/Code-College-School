package org.example.asm.Controller;

import org.example.asm.Model.Task;
import org.example.asm.Model.User;
import org.example.asm.Repository.TaskRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/board")
    public String staffBoard(Model model, Principal principal, HttpSession session) {
        String username = "Guest";
        Integer userId = null;
        if (principal != null) {
            username = principal.getName();
            User user = (User) session.getAttribute("loggedInUser");
            if (user != null) {
                userId = user.getId();
            }
        }
        List<Task> tasks = taskRepository.findAll();
        List<String> dueSoonMessages = new ArrayList<>();
        List<Task> dueSoonTasks = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Task task : tasks) {
            if (task.getAssignedTo() != null && userId != null && task.getAssignedTo().getId().equals(userId)) {
                if (task.getDeadline() != null) {
                    long hours = ChronoUnit.HOURS.between(now, task.getDeadline());
                    if (hours >= 0 && hours <= 24) {
                        dueSoonTasks.add(task);
                    }
                }
            }
        }
        model.addAttribute("username", username);
        model.addAttribute("tasks", tasks);
        model.addAttribute("dueSoonMessages", dueSoonMessages);
        model.addAttribute("dueSoonTasks", dueSoonTasks);
        return "staff-board";
    }
}