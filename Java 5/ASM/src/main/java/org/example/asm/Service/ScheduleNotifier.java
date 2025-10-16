package org.example.asm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ScheduleNotifier {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // chạy mỗi 30 giây
    @Scheduled(fixedRate = 10000)
    public void sendSchedulePing() {
        Map<String, Object> message = new HashMap<>();
        message.put("time", LocalTime.now().toString());
        message.put("message", "⏰ ScheduleTask đang hoạt động!");
        message.put("type", "info");

        // Gửi đến tất cả client đang kết nối
        messagingTemplate.convertAndSend("/topic/schedule", message);
        System.out.println("✅ ScheduleTask gửi thông báo lúc: " + LocalTime.now());
    }
}
