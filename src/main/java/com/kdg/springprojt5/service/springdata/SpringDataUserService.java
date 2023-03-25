package com.kdg.springprojt5.service.springdata;

import com.kdg.springprojt5.domain.User;
import com.kdg.springprojt5.repository.springdata.SpringDataArtistRepository;
import com.kdg.springprojt5.repository.springdata.SpringDataUserRepository;
import com.kdg.springprojt5.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository
public class SpringDataUserService implements UserService {
    private final SpringDataUserRepository userRepository;

    private final SpringDataArtistRepository artistRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public SpringDataUserService(SpringDataUserRepository userRepository, SpringDataArtistRepository artistRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
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
        User user = userRepository.getReferenceById(id);
//        user.setArtists(artistRepository.getArtistsByUser(user));
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
