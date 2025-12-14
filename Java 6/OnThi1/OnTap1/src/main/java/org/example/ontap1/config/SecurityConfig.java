package org.example.ontap1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Tắt CSRF để test API dễ dàng (POST/PUT/DELETE không bị chặn)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Kích hoạt cấu hình CORS (để Frontend gọi được)
                .cors(Customizer.withDefaults())

                // 3. Cấu hình quyền truy cập
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Bắt buộc đăng nhập với mọi request
                )

                // 4. Sử dụng Basic Auth (Hiện popup trình duyệt hoặc gửi Header Auth)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // 5. Tạo tài khoản cứng theo yêu cầu đề bài
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("TH03089")  // MSSV
                .password("SD20202")  // Lớp
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    // 6. Cấu hình CORS chi tiết (Fix lỗi Network Error bên VueJS)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Cho phép mọi nguồn (VueJS, Postman...)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*")); // Cho phép mọi header (Authorization, Content-Type...)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}