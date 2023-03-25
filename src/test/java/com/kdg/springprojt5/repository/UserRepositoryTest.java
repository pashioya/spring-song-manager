package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.User;
import com.kdg.springprojt5.repository.springdata.SpringDataUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private SpringDataUserRepository userRepository;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userRepository.save(user);
    }

    @Test
    public void findByUsername_caseSensitive() {
        User foundUser = userRepository.findByUsername("testuser");
        Assertions.assertNull(foundUser, "Found user should be null");

        foundUser = userRepository.findByUsername("Testuser");
        Assertions.assertNull(foundUser, "Found user should be null");

        foundUser = userRepository.findByUsername("TESTUSER");
        Assertions.assertNull(foundUser, "Found user should be null");

        foundUser = userRepository.findByUsername("testuser1");
        Assertions.assertNull(foundUser, "Found user should be null");
    }
}
