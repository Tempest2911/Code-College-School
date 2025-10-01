package org.example.asm.Service;

import org.example.asm.DTO.TaskDTO;
import org.example.asm.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Broadcast đến tất cả client
    public void broadcastTask(Task task, String action) {
        TaskDTO dto = TaskDTO.fromEntity(task, action);

        // /topic/tasks -> tất cả staff sẽ nhận được
        messagingTemplate.convertAndSend("/topic/tasks", dto);

        // /user/queue/tasks -> gửi riêng cho assigned user (nếu có)
        if (task.getAssignedTo() != null) {
            messagingTemplate.convertAndSendToUser(
                    task.getAssignedTo().getUsername(),   // dùng username làm session
                    "/queue/tasks",
                    dto
            );
        }
    }
}
