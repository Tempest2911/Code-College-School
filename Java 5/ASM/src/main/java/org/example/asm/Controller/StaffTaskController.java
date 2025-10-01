package org.example.asm.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.asm.Model.Task;
import org.example.asm.Model.User;
import org.example.asm.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff/task")
public class StaffTaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/list")
    public String myTasks(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/asm/login";

        // Add current staff to model for Thymeleaf
        model.addAttribute("currentStaff", user);
        // Staff only sees their own tasks
        model.addAttribute("tasks", taskRepository.findByAssignedTo_Id(user.getId()));

        return "staff-list";
    }

    // Update status
    @PostMapping("/status/{id}")
    public String updateStatus(@PathVariable Integer id,
                               @RequestParam String status,
                               HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/asm/login";
        }

        Task task = taskRepository.findById(id).orElseThrow();

        // Ensure staff can only update their own tasks
        if ("Staff".equals(user.getRole()) &&
                (task.getAssignedTo() == null || !task.getAssignedTo().getId().equals(user.getId()))) {
            return "redirect:/staff/task/list";
        }

        // Normalize status (capitalize first letters)
        String normalizedStatus;
        switch (status.toLowerCase()) {
            case "todo" -> normalizedStatus = "Todo";
            case "in-progress", "inprogress" -> normalizedStatus = "In-Progress";
            case "done" -> normalizedStatus = "Done";
            default -> normalizedStatus = "Todo";
        }
        task.setStatus(normalizedStatus);

        taskRepository.save(task);

        if ("Admin".equals(user.getRole())) {
            return "redirect:/admin/task/list";
        }
        return "redirect:/staff/task/list";
    }
}