package com.juandev.queuems.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    //FILTRO DE ACCESO A ENDPOINTS
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)//Deshabilita csrf
            .authorizeHttpRequests(authRequest ->
                    authRequest
                            .requestMatchers("/auth/**").permitAll()//Permite endpoint que inician con /auth
                            .anyRequest().authenticated()
            )
            .formLogin(withDefaults())
            .build();
    }
}
