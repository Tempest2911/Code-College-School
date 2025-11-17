package org.example.springoathwithgoogle.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class Appconfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource, PasswordEncoder passwordEncoder) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(config -> {
                    config.requestMatchers("/poly/**").authenticated();
                    config.anyRequest().permitAll();
                })
                .formLogin(config -> {
                    config.loginPage("/login");
                    config.loginProcessingUrl("/auth/check");
                    config.defaultSuccessUrl("/");
                    config.failureUrl("/login");
                    config.usernameParameter("username");
                    config.passwordParameter("password");
                })
                // Cấu hình google OAuth2 login
                .oauth2Login(config -> {
                    config.loginPage("/login");
                    config.defaultSuccessUrl("/");
                    config.failureUrl("/login");
                })
                .rememberMe(config -> {
                    config.tokenValiditySeconds(86400);
                    config.rememberMeParameter("remember-me");
                })
                .logout(config -> {
                    ;
                    config.logoutUrl("/logout");
                    config.logoutSuccessUrl("/");
                    config.clearAuthentication(true);
                    config.invalidateHttpSession(true);
                    config.deleteCookies("remember-me", "JSESSIONID");
                });
        return http.build();
    }
}
