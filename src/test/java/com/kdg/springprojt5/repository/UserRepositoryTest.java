package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.User;
import com.kdg.springprojt5.domain.UserRole;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userRepository.save(new User(
                "testuser",
                passwordEncoder.encode("password"),
                UserRole.USER
        ));
    }

    @Test
    public void findByUsernameIscaseSensitive() {
        User foundUser = userRepository.findByUsername("testuser");
        Assertions.assertNotNull(foundUser, "Found User Shouldn't be null");

        foundUser = userRepository.findByUsername("Testuser");
        Assertions.assertNull(foundUser, "Found user should be null");

        foundUser = userRepository.findByUsername("TESTUSER");
        Assertions.assertNull(foundUser, "Found user should be null");

        foundUser = userRepository.findByUsername("testuser1");
        Assertions.assertNull(foundUser, "Found user should be null");
    }

    @Test
    void usernameShouldBeUnique() {
        User user = new User(
                "testuser",
                passwordEncoder.encode("password"),
                UserRole.USER
        );
        assertThrows(
                DataIntegrityViolationException.class,
                () -> userRepository.save(user)
        );
    }

    @AfterAll
    public void tearDown() {
        userRepository.deleteAll();
    }

}
