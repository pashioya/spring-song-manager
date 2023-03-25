package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.User;

public interface UserRepository {

    User findByUsername(String username);

}
