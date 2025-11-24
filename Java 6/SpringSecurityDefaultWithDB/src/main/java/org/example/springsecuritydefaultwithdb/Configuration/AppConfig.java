package org.example.springsecuritydefaultwithdb.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity

public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource, PasswordEncoder passwordEncoder) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("select username, password, enabled from users where username = ?");
        manager.setAuthoritiesByUsernameQuery("select username, authority from authorities where username = ?");

        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> {
//            auth.requestMatchers("/poly/**").authenticated();
            auth.requestMatchers("poly/url1").authenticated();
            auth.requestMatchers("poly/url2").hasRole("ADMIN");
            auth.requestMatchers("poly/url3").hasRole("USER");
            auth.requestMatchers("poly/url4").hasAnyRole("USER", "ADMIN");

            auth.anyRequest().permitAll();
        });

        http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
        http.rememberMe(config -> {
            config.tokenValiditySeconds(86400);
            config.rememberMeParameter("remember-me");
            config.userDetailsService(userDetailsService);
        });
        return http.build();
    }
}
