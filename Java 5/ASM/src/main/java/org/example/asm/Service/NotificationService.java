package org.example.asm.Service;

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
        if (task == null) return;

        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setStatus(task.getStatus() != null ? task.getStatus() : "UNKNOWN");
        dto.setPriority(task.getPriority() != null ? task.getPriority() : "NONE");
        dto.setDeadline(task.getDeadline() != null ? task.getDeadline().toString() : null);
        dto.setDepartment(task.getDepartment() != null ? task.getDepartment().getName() : null);
        dto.setAssignedTo(task.getAssignedTo() != null ? task.getAssignedTo().getFullName() : null);
        dto.setAction(action);

        // 🔥 Broadcast cho tất cả Staff/Admin đang subscribe
        messagingTemplate.convertAndSend("/topic/tasks", dto);
    }
}
