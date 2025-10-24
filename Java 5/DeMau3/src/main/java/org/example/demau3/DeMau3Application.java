package org.example.demau3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DeMau3Application {

    public static void main(String[] args) {
        SpringApplication.run(DeMau3Application.class, args);
    }

}
