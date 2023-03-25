package com.kdg.springprojt5.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    private final Long userId;
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(username, password, authorities);
        this.userId=userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "userId=" + userId +
                '}';
    }
}
