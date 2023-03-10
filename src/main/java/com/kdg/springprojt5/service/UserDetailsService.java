package com.kdg.springprojt5.service;

import com.kdg.springprojt5.domain.User;

public interface UserDetailsService {
    User loadUserByUsername(String user);
}
