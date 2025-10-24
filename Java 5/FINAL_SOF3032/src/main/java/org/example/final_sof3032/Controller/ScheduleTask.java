package org.example.final_sof3032.Controller;

import org.example.final_sof3032.Repository.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduleTask {
    @Autowired
    XeRepository sanPhamRepository;

    @Scheduled(fixedRate = 1000)
    public void printCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        System.out.println("Ngày giờ hiện tại: " + now.format(formatter));
        long totalRecords = sanPhamRepository.count();
        System.out.println("Số lượng bản ghi của bảng xe trong CSDL là: " + totalRecords);
    }
}
