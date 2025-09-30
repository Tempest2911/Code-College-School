package org.example.asm.Controller;

import org.example.asm.Model.Department;
import org.example.asm.Model.Task;
import org.example.asm.Model.User;
import org.example.asm.Repository.DepartmentRepository;
import org.example.asm.Repository.TaskRepository;
import org.example.asm.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/task")
public class AdminTaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Hiển thị form + danh sách
    @GetMapping("/list")
    public String showTaskList(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("staffList", userRepository.findByRole("staff"));
        model.addAttribute("departments", departmentRepository.findAll());
        return "admin-list";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task,
                           Principal principal) {

        User creator = null;

        if (principal != null) {
            // Có login thì lấy user từ DB
            creator = userRepository.findByUsername(principal.getName());
        } else {
            // Không login thì fallback về admin id=1
            creator = userRepository.findById(1).orElse(null);
        }

        task.setCreatedBy(creator);


        // Nếu không chọn staff thì set null
        if (task.getAssignedTo() != null && task.getAssignedTo().getId() == null) {
            task.setAssignedTo(null);
        }

        // Nếu không chọn department thì set null
        if (task.getDepartment() != null && task.getDepartment().getId() == null) {
            task.setDepartment(null);
        }

        taskRepository.save(task);
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
        taskRepository.deleteById(id);
        return "redirect:/admin/task/list";
    }
}
