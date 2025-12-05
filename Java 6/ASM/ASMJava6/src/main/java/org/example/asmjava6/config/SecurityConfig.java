package org.example.asmjava6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Tắt CSRF và CORS để cho phép gọi API từ bên ngoài dễ dàng
        http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable());

        // Cấu hình quyền truy cập
        http.authorizeHttpRequests(auth -> {
            // Cho phép tất cả mọi người truy cập vào các đường dẫn bắt đầu bằng /rest/**
            auth.requestMatchers("/rest/**").permitAll();
            auth.requestMatchers("/assets/**", "/images/**").permitAll(); // Mở tài nguyên tĩnh

            // Các đường dẫn khác tạm thời mở hết để test cho dễ (sau này sẽ chặn sau)
            auth.anyRequest().permitAll();
        });

        return http.build();
    }
}