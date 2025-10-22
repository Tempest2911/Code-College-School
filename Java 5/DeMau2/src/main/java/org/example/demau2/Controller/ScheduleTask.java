package org.example.demau2.Controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduleTask {
    private final LocalDateTime startTime = LocalDateTime.now();

        @Scheduled(fixedRate = 1000)
        public void printCurrentTime() {
            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            System.out.println("Giờ hiện tại: " + now.format(formatter));

            Duration duration = Duration.between(startTime, now);
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();

            System.out.printf("Ứng dụng đã chạy được: %02d:%02d:%02d%n", hours, minutes, seconds);
            System.out.println("--------------------------------------------------");
        }
}
