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

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        String editor = principal != null ? principal.getName() : "unknown";
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
        taskService.broadcastTask(saved, "CREATED", editor);

        return "redirect:/admin/task/list";
    }


    // 👉 List Task (phân trang riêng, không search)
    @GetMapping("/list")
    public String showTaskList(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "sort", required = false) String sort,
            Model model) {
        Pageable pageable;
        if (sort != null && sort.equals("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("deadline").descending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("deadline").ascending());
        }
        Page<Task> taskPage = taskRepository.findAll(pageable);
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", taskPage.getContent());
        model.addAttribute("totalPages", taskPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("staffList", userRepository.findByRole("staff"));
        model.addAttribute("departments", departmentRepository.findAll());
        return "admin-list";
    }

    @PostMapping("/save")
    public String updateTask(@ModelAttribute Task task, Principal principal) {
        String editor = principal != null ? principal.getName() : "unknown";
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
        taskService.broadcastTask(updated, "UPDATED", editor);

        // ✅ Nếu đổi AssignedTo → gửi "DELETED" cho oldAssignee
        String newAssignedUsername = (updated.getAssignedTo() != null)
                ? updated.getAssignedTo().getUsername()
                : null;

        if (oldAssignedUsername != null && !oldAssignedUsername.equals(newAssignedUsername)) {
            taskService.notifyRemovedFromOldAssignee(updated, oldAssignedUsername, editor);
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
        // Bổ sung biến phân trang và search mặc định
        model.addAttribute("totalPages", 1);
        model.addAttribute("currentPage", 0);
        model.addAttribute("size", 10);
        model.addAttribute("keyword", "");
        model.addAttribute("departmentId", "");
        model.addAttribute("startDate", "");
        model.addAttribute("endDate", "");
        model.addAttribute("sort", "asc");
        return "admin-list";
    }

    // 👉 Delete Task
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id, Principal principal) {
        String editor = principal != null ? principal.getName() : "unknown";
        taskRepository.findById(id).ifPresent(task -> {
            taskRepository.delete(task);
            // ✅ Broadcast realtime
            taskService.broadcastTask(task, "DELETED", editor);
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


    // 👉 Search (theo tiêu đề, phòng ban, và khoảng thời gian)
    @GetMapping("/search")
    public String searchTasks(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "departmentId", required = false) Integer departmentId,
            @RequestParam(value = "startDate", required = false) String startDateStr,
            @RequestParam(value = "endDate", required = false) String endDateStr,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            Model model) {
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        if (startDateStr != null && !startDateStr.isEmpty()) {
            startDate = LocalDateTime.parse(startDateStr, formatter);
        }
        if (endDateStr != null && !endDateStr.isEmpty()) {
            endDate = LocalDateTime.parse(endDateStr, formatter);
        }
        Pageable pageable;
        if (sort != null && sort.equals("desc")) {
            pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by("deadline").descending());
        } else {
            pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by("deadline").ascending());
        }
        var taskPage = taskRepository.searchTasksPage(
                (keyword != null && !keyword.isEmpty()) ? keyword : null,
                departmentId,
                startDate,
                endDate,
                pageable
        );
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", taskPage.getContent());
        model.addAttribute("totalPages", taskPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("staffList", userRepository.findByRole("staff"));
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("keyword", keyword);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("startDate", startDateStr);
        model.addAttribute("endDate", endDateStr);
        model.addAttribute("sort", sort);
        return "admin-list";
    }

    @GetMapping("/sortLowToHigh")
    public String sortLowToHigh(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Task> taskPage = taskRepository.findAll(pageable);
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", taskPage.getContent());
        model.addAttribute("totalPages", taskPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("staffList", userRepository.findByRole("staff"));
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("keyword", "");
        model.addAttribute("departmentId", "");
        model.addAttribute("startDate", "");
        model.addAttribute("endDate", "");
        model.addAttribute("sort", "asc");
        return "admin-list";
    }

    @GetMapping("/sortHighToLow")
    public String sortHighToLow(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Task> taskPage = taskRepository.findAll(pageable);
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", taskPage.getContent());
        model.addAttribute("totalPages", taskPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("staffList", userRepository.findByRole("staff"));
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("keyword", "");
        model.addAttribute("departmentId", "");
        model.addAttribute("startDate", "");
        model.addAttribute("endDate", "");
        model.addAttribute("sort", "desc");
        return "admin-list";
    }

}
