package com.kdg.springprojt5.service;

import com.kdg.springprojt5.domain.User;

public interface UserService {
    User getByUserName(String username);

    User saveUser(User user);

    User getUserById(long id);

    void deleteUser(long id);
}
