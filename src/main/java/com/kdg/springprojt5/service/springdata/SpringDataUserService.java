package com.kdg.springprojt5.service.springdata;

import com.kdg.springprojt5.domain.User;
import com.kdg.springprojt5.repository.springdata.SpringDataUserRepository;
import com.kdg.springprojt5.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@Profile("springData")
public class SpringDataUserService implements UserService {
    private final SpringDataUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public SpringDataUserService(SpringDataUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
