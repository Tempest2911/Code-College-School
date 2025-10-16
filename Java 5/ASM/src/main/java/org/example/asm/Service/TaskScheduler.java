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

    // ✅ In log định kỳ mỗi 30 giây để dễ kiểm tra hoạt động
    @Scheduled(fixedRate = 10000)
    public void heartbeat() {
        logger.info("✅ TaskScheduler đang hoạt động... {}", LocalDateTime.now());
    }
}
