package com.kdg.springprojt5.security;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .csrf()
                .and()
                .csrf()
                .ignoringRequestMatchers("/api/song") // This is for testing the client side
                .and()
                .cors()
                .and()
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/js/**", "/css/**", "/webjars/**", "/favicon.ico").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/**") // syntax by which you can specify nested paths generically, like regexes
                        .authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/**") // syntax by which you can specify nested paths generically, like regexes
                        .authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/**") // syntax by which you can specify nested paths generically, like regexes
                        .authenticated()
                        .requestMatchers("/", "/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/song/album/**").permitAll()  // This Is For testing the client side
                        .anyRequest().authenticated())
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:9000", "http://localhost:8080");
            }
        };
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
