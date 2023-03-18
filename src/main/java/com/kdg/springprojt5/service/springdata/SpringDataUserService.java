package com.kdg.springprojt5.service.springdata;



import com.kdg.springprojt5.domain.User;
import com.kdg.springprojt5.repository.springdata.SpringDataUserRepository;
import com.kdg.springprojt5.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class SpringDataUserService implements UserService {
    private final SpringDataUserRepository userRepository;

    public SpringDataUserService(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

}
