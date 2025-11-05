package org.example.springcustomauthentication.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

    // Stub User
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        String password = passwordEncoder.encode("123");
        UserDetails user1 = User.withUsername("poly")
                .password(password)
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("admin")
                .password(password)
                .roles("ADMIN")
                .build();
        UserDetails user3 = User.withUsername("both")
                .password(password)
                .roles("BOTH")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(config -> {
            config.anyRequest().permitAll();
        });
        http.formLogin(config -> {
            config.loginPage("/auth/login");
            config.loginProcessingUrl("/auth/login");
            config.defaultSuccessUrl("/auth/success");
            config.failureUrl("/auth/fail");
            config.permitAll();
            config.usernameParameter("username");
            config.passwordParameter("password");
        });
        //Ghi nhớ
        http.rememberMe(config -> {
            config.tokenValiditySeconds(5 * 24 * 60 * 60);
            config.rememberMeCookieName("remember-me");
            config.rememberMeParameter("remember-me");
        });

        http.logout(config -> {
            config.logoutUrl("/auth/logout");
            config.logoutSuccessUrl("/auth/exit");
            config.deleteCookies("JSESSIONID", "remember-me");
            config.invalidateHttpSession(true);
            config.permitAll();
        });
        return http.build();
    }
}
