package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private SpringDataUserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserRepositoryTest(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("testpassword"));
        userRepository.save(user);
    }

    @Test
    public void findByUsername_caseSensitive() {
        User foundUser = userRepository.findByUsername("testuser");
        Assertions.assertNotNull(foundUser, "Found User Shouldn't be null");

        foundUser = userRepository.findByUsername("Testuser");
        Assertions.assertNull(foundUser, "Found user should be null");

        foundUser = userRepository.findByUsername("TESTUSER");
        Assertions.assertNull(foundUser, "Found user should be null");

        foundUser = userRepository.findByUsername("testuser1");
        Assertions.assertNull(foundUser, "Found user should be null");
    }
}
