package com.kdg.springprojt5.service;

import com.kdg.springprojt5.domain.User;

import java.util.List;

public interface UserService {
    User getByUserName(String username);

    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);
}
