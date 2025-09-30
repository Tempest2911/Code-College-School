package org.example.asm.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.asm.DTO.TaskDTO;
import org.example.asm.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendTaskNotification(Task task, String action) {
        TaskDTO dto = new TaskDTO(task, action);
        messagingTemplate.convertAndSend("/topic/tasks", dto);

        // Nếu muốn gửi riêng cho staff được assign
        if (task.getAssignedTo() != null) {
            messagingTemplate.convertAndSendToUser(
                    task.getAssignedTo().getUsername(),
                    "/queue/tasks",
                    dto
            );
        }
    }
}
