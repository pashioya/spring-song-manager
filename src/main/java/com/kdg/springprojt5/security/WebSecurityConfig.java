package com.kdg.springprojt5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/js/**", "/css/**", "/webjars/**", "/favicon.ico").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/**") // syntax by which you can specify nested paths generically, like regexes
                        .authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/**") // syntax by which you can specify nested paths generically, like regexes
                        .authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/**") // syntax by which you can specify nested paths generically, like regexes
                        .authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/**") // syntax by which you can specify nested paths generically, like regexes
                        .authenticated()
                        .requestMatchers("/", "/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()
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
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
