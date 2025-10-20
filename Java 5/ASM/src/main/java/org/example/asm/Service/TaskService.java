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

    // Gửi broadcast cập nhật cho tất cả client (chỉ để cập nhật giao diện)
    public void broadcastTask(Task task, String action, String editor) {
        TaskDTO dto = TaskDTO.fromEntity(task, action, editor);
        // Gửi cho tất cả client để cập nhật giao diện
        messagingTemplate.convertAndSend("/topic/tasks", dto);
        // Chỉ gửi thông báo toast cho editor (người thao tác)
        if (editor != null) {
            messagingTemplate.convertAndSendToUser(
                editor,
                "/queue/tasks",
                dto
            );
        }
    }

    public void notifyRemovedFromOldAssignee(Task task, String oldUsername, String editor) {
        if (oldUsername == null) return;
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setAction("DELETED");
        dto.setTitle(task.getTitle());
        dto.setEditor(editor);
        // Chỉ gửi thông báo toast cho editor khi xóa khỏi acc cũ
        if (editor != null) {
            messagingTemplate.convertAndSendToUser(editor, "/queue/tasks", dto);
        }
    }


}
