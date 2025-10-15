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

    // Gửi broadcast + newAssignee
    public void broadcastTask(Task task, String action) {
        TaskDTO dto = TaskDTO.fromEntity(task, action);

        // Gửi cho tất cả (dashboard, admin, board chung)
        messagingTemplate.convertAndSend("/topic/tasks", dto);

        // Gửi riêng cho newAssignee (nếu có)
        if (task.getAssignedTo() != null && task.getAssignedTo().getUsername() != null) {
            messagingTemplate.convertAndSendToUser(
                    task.getAssignedTo().getUsername(),
                    "/queue/tasks",
                    dto
            );
        }
    }

    public void notifyRemovedFromOldAssignee(Task task, String oldUsername) {
        if (oldUsername == null) return;

        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setAction("DELETED");
        dto.setTitle(task.getTitle()); // ⚡ thêm để client toast đẹp

        messagingTemplate.convertAndSendToUser(oldUsername, "/queue/tasks", dto);
    }


}

