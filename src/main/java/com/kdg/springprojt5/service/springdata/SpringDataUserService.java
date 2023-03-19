package com.kdg.springprojt5.service.springdata;

import com.kdg.springprojt5.domain.User;
import com.kdg.springprojt5.repository.springdata.SpringDataUserRepository;
import com.kdg.springprojt5.service.UserService;
import org.springframework.stereotype.Repository;

@Repository
public class SpringDataUserService implements UserService {
    private final SpringDataUserRepository userRepository;

    public SpringDataUserService(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
