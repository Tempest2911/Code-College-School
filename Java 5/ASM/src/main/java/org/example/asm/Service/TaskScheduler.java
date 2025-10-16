package org.example.asm.Service;

import org.example.asm.Model.Task;
import org.example.asm.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TaskScheduler {
    private static final Logger logger = LoggerFactory.getLogger(TaskScheduler.class);
    @Autowired
    private TaskRepository taskRepository;

    // Chạy mỗi 5 phút
    @Scheduled(fixedRate = 300000)
    public void checkTasksNearDeadline() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextDay = now.plusDays(1);
        List<Task> tasks = taskRepository.findByDeadlineBetween(now, nextDay);
        for (Task task : tasks) {
            logger.info("[ScheduleTask] Task gần đến hạn: {} - Deadline: {}", task.getTitle(), task.getDeadline());
        }
    }

    // Ghi log mỗi phút để kiểm tra schedule hoạt động
    @Scheduled(fixedRate = 60000)
    public void logHeartbeat() {
        logger.info("[ScheduleTask] Heartbeat: Scheduled task đang hoạt động lúc {}", LocalDateTime.now());
    }
}
