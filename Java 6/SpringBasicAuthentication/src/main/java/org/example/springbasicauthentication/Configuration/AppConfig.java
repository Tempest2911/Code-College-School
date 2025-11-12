package org.example.springbasicauthentication.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        String password = passwordEncoder.encode("123");
        UserDetails user1 = User.withUsername("user")
                .password(password)
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("admin")
                .password(password)
                .roles("ADMIN")
                .build();
        UserDetails user3 = User.withUsername("both")
                .password(password)
                .roles("USER","ADMIN") // nếu bạn muốn 'both' có cả 2 role
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Không disable CSRF/cors ở đây (nếu bạn đang dev và cần disable thì comment rõ lý do)
        http
                .cors(Customizer.withDefaults()) // nếu cần custom CORS, cấu hình cụ thể
                .authorizeHttpRequests(auth -> auth
                        // cho phép các tài nguyên tĩnh và trang login mà không cần auth
                        .requestMatchers("/", "/login**", "/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll()
                        .requestMatchers("/poly/url2").hasRole("USER")
                        .requestMatchers("/poly/url3").hasRole("ADMIN")
                        .requestMatchers("/poly/**").authenticated() // các route khác của /poly
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        // nếu bạn muốn trang login tùy biến: .loginPage("/login/form")
                        .permitAll()
                )
                .rememberMe(r -> r
                                .tokenValiditySeconds(86400)
                        // .key("someSecretKey") // cấu hình key cho production
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}

