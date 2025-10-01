package org.example.asm.Controller;

import org.example.asm.Model.Department;
import org.example.asm.Model.Task;
import org.example.asm.Model.User;
import org.example.asm.Repository.DepartmentRepository;
import org.example.asm.Repository.TaskRepository;
import org.example.asm.Repository.UserRepository;
import org.example.asm.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
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

    @Autowired
    private TaskService taskService;   // ✅ chỉ giữ lại TaskService


    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task, Principal principal) {
        User creator = null;

        if (principal != null) {
            creator = userRepository.findByUsername(principal.getName());
        }
        if (creator == null) {
            creator = userRepository.findById(1).orElse(null); // fallback admin
        }

        task.setCreatedBy(creator);

        // ✅ Gắn AssignedTo từ ID (nếu có)
        if (task.getAssignedTo() != null && task.getAssignedTo().getId() != null) {
            User assigned = userRepository.findById(task.getAssignedTo().getId())
                    .orElse(null);
            task.setAssignedTo(assigned);
        } else {
            task.setAssignedTo(null);
        }

        // ✅ Gắn Department từ ID (nếu có)
        if (task.getDepartment() != null && task.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(task.getDepartment().getId())
                    .orElse(null);
            task.setDepartment(dept);
        } else {
            task.setDepartment(null);
        }

        Task saved = taskRepository.save(task);

        // ✅ Broadcast realtime
        taskService.broadcastTask(saved, "CREATED");

        return "redirect:/admin/task/list";
    }

    // 👉 List Task (lọc theo trạng thái)
    @GetMapping("/list")
    public String showTaskList(@RequestParam(required = false) String status, Model model) {
        List<Task> tasks = (status != null && !status.isEmpty())
                ? taskRepository.findByStatus(status)
                : taskRepository.findAll();

        model.addAttribute("task", new Task());
        model.addAttribute("tasks", tasks);
        model.addAttribute("staffList", userRepository.findByRole("staff"));
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("selectedStatus", status);

        return "admin-list";
    }

    @PostMapping("/save")
    public String updateTask(@ModelAttribute Task task, Principal principal) {
        User creator = (principal != null)
                ? userRepository.findByUsername(principal.getName())
                : userRepository.findById(1).orElse(null);
        task.setCreatedBy(creator);

        // Lấy task cũ để so sánh
        Task existing = taskRepository.findById(task.getId()).orElse(null);
        String oldAssignedUsername = (existing != null && existing.getAssignedTo() != null)
                ? existing.getAssignedTo().getUsername()
                : null;

        // Gắn AssignedTo mới
        if (task.getAssignedTo() != null && task.getAssignedTo().getId() != null) {
            User assigned = userRepository.findById(task.getAssignedTo().getId()).orElse(null);
            task.setAssignedTo(assigned);
        } else {
            task.setAssignedTo(null);
        }

        // Gắn Department mới
        if (task.getDepartment() != null && task.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(task.getDepartment().getId()).orElse(null);
            task.setDepartment(dept);
        } else {
            task.setDepartment(null);
        }

        Task updated = taskRepository.save(task);

        // ✅ Broadcast cho all + newAssignee
        taskService.broadcastTask(updated, "UPDATED");

        // ✅ Nếu đổi AssignedTo → gửi "DELETED" cho oldAssignee
        String newAssignedUsername = (updated.getAssignedTo() != null)
                ? updated.getAssignedTo().getUsername()
                : null;

        if (oldAssignedUsername != null && !oldAssignedUsername.equals(newAssignedUsername)) {
            taskService.notifyRemovedFromOldAssignee(updated, oldAssignedUsername);
        }

        return "redirect:/admin/task/list";
    }

    // 👉 Edit form
    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Integer id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        model.addAttribute("task", task);
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("staffList", userRepository.findByRole("staff"));
        model.addAttribute("departments", departmentRepository.findAll());
        return "admin-list";
    }

    // 👉 Delete Task
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        taskRepository.findById(id).ifPresent(task -> {
            taskRepository.delete(task);

            // ✅ Broadcast realtime
            taskService.broadcastTask(task, "DELETED");
        });

        return "redirect:/admin/task/list";
    }

    // 👉 Dashboard thống kê
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Object[]> stats = taskRepository.countTasksByUser();

        List<String> labels = new ArrayList<>();
        List<Long> data = new ArrayList<>();

        for (Object[] row : stats) {
            labels.add((String) row[0]); // username
            data.add((Long) row[1]);     // số task
        }

        model.addAttribute("labels", labels);
        model.addAttribute("data", data);

        return "dashboard";
    }
}
