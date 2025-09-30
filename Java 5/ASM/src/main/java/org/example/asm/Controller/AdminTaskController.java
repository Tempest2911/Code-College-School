package org.example.asm.Controller;

import org.example.asm.Model.Department;
import org.example.asm.Model.Task;
import org.example.asm.Model.User;
import org.example.asm.Repository.DepartmentRepository;
import org.example.asm.Repository.TaskRepository;
import org.example.asm.Repository.UserRepository;
import org.example.asm.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin/task")
public class AdminTaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private NotificationService notificationService;

    // Create task nhanh
    @PostMapping("/task")
    public String createTask(@ModelAttribute Task task) {
        taskRepository.save(task);

        // Gửi realtime notify
        notificationService.sendTaskNotification(task, "CREATED");

        return "redirect:/admin/task/list";
    }

    // Hiển thị form + danh sách
    @GetMapping("/list")
    public String showTaskList(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("staffList", userRepository.findByRole("staff"));
        model.addAttribute("departments", departmentRepository.findAll());
        return "admin-list";
    }

    // Save task (create/update)
    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task, Principal principal) {
        User creator = null;

        if (principal != null) {
            creator = userRepository.findByUsername(principal.getName());
        } else {
            creator = userRepository.findById(1).orElse(null); // fallback admin
        }

        task.setCreatedBy(creator);

        if (task.getAssignedTo() != null && task.getAssignedTo().getId() == null) {
            task.setAssignedTo(null);
        }

        if (task.getDepartment() != null && task.getDepartment().getId() == null) {
            task.setDepartment(null);
        }

        taskRepository.save(task);

        // Notify realtime
        notificationService.sendTaskNotification(task, "UPDATED");

        return "redirect:/admin/task/list";
    }

    // Edit task
    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Integer id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        model.addAttribute("task", task);
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("staffList", userRepository.findByRole("staff"));
        model.addAttribute("departments", departmentRepository.findAll());
        return "admin-list";
    }

    // Delete task
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        taskRepository.findById(id).ifPresent(task -> {
            taskRepository.delete(task);
            notificationService.sendTaskNotification(task, "DELETED");
        });

        return "redirect:/admin/task/list";
    }
}
