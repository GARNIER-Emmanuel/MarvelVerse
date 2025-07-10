package com.manu.marvel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()               // désactive CSRF pour tests POST avec Postman
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());  // autorise tout
        return http.build();
    }
}
