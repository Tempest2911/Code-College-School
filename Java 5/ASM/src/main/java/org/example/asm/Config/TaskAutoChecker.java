package org.example.asm.Config;

import org.example.asm.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskAutoChecker {  // ✅ Đổi tên class

    @Autowired
    private TaskRepository taskRepository;

    @Scheduled(fixedRate = 60000)
    public void checkOverdueTasks() {
        var tasks = taskRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        tasks.forEach(t -> {
            if (t.getDeadline() != null && t.getDeadline().isBefore(now) && !"Done".equals(t.getStatus())) {
                t.setStatus("Overdue");
                taskRepository.save(t);
            }
        });

        System.out.println("✅ Scheduler chạy lúc: " + now);
    }
}
