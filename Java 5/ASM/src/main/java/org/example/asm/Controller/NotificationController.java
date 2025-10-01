package org.example.asm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Gửi notification đến tất cả client subscribe topic này
    public void sendNotification(String message) {
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }

    // Nếu muốn gửi riêng cho 1 user theo username
    public void sendPrivateNotification(String username, String message) {
        messagingTemplate.convertAndSendToUser(username, "/queue/notifications", message);
    }

    // Client có thể gửi tin nhắn ngược lên server (nếu cần)
    @MessageMapping("/hello")
    public void greeting(String msg) {
        messagingTemplate.convertAndSend("/topic/notifications", "User says: " + msg);
    }
}