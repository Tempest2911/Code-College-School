package org.example.consumeapiwithaxios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ConsumeApiWithAxiosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeApiWithAxiosApplication.class, args);
    }

}
