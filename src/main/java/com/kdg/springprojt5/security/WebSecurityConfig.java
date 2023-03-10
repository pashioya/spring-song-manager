package com.kdg.springprojt5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/js/**", "/css/**", "/webjars/**").permitAll()
//                remove later, only for testing
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers(HttpMethod.POST).permitAll()
                        .requestMatchers(HttpMethod.PUT).permitAll()
                        .requestMatchers(HttpMethod.DELETE).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
//
                        .requestMatchers("/resources/**").permitAll()
                        .requestMatchers("/", "/register").permitAll()
                        .anyRequest().authenticated())
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable();// disable CSRF protection
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/js/**", "/css/**", "/webjars/**");
    }
}
