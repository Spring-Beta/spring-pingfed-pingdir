package com.example.feddir.config;

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
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login/**", "/error/**", "/").permitAll() // Allow unauthenticated access to these paths
                        .anyRequest().authenticated()) // All other requests need authentication
                .oauth2Login(oauth2 -> oauth2 // Enable OAuth2 login
                        .loginPage("/login") // Custom login page if needed
                        .defaultSuccessUrl("/home", true) // Redirect after successful login
                        .failureUrl("/login?error=true") // Redirect on failure
                );

        return http.build();
    }
}
