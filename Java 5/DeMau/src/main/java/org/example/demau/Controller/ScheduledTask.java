package org.example.demau.Controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTask {

    private final LocalDateTime startTime = LocalDateTime.now(); // Lưu thời điểm bắt đầu

    // Mỗi giờ chạy 1 lần
    @Scheduled(fixedRate = 1000) // mỗi 10 giây chạy thử
    public void printCurrentTime() {
        LocalDateTime now = LocalDateTime.now();

        // In ra giờ hiện tại
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Giờ hiện tại: " + now.format(formatter));

        // Tính thời gian đã chạy
        Duration duration = Duration.between(startTime, now);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        System.out.printf("Ứng dụng đã chạy được: %02d:%02d:%02d%n", hours, minutes, seconds);
    }
}
