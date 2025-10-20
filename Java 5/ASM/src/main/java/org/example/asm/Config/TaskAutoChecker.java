package org.example.asm.Config;

import org.example.asm.Repository.TaskRepository;
import org.example.asm.Service.TaskService;
import org.example.asm.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskAutoChecker {  // ✅ Đổi tên class

    @Autowired
    private TaskRepository taskRepository;

    // Thêm service để broadcast realtime khi status thay đổi
    @Autowired
    private TaskService taskService;

    // Chạy mỗi 10 giây (tạm thời cho demo) — đổi lại 60000 sau khi demo
    @Scheduled(fixedRate = 10000)
    public void checkOverdueTasks() {
        var tasks = taskRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        tasks.forEach(t -> {
            if (t.getDeadline() != null && t.getDeadline().isBefore(now) && !"Done".equals(t.getStatus())) {
                t.setStatus("Overdue");
                Task saved = taskRepository.save(t);

                // Gửi realtime cho các client để cập nhật giao diện
                try {
                    taskService.broadcastTask(saved, "UPDATED", "system");
                } catch (Exception ex) {
                    System.out.println("[TaskAutoChecker] Failed to broadcast websocket update: " + ex.getMessage());
                }
            }
        });

        System.out.println("✅ Scheduler chạy lúc: " + now);
    }
}
