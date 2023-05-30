package com.kdg.springprojt5.service.springdata;

import com.kdg.springprojt5.domain.User;
import com.kdg.springprojt5.repository.AlbumRepository;
import com.kdg.springprojt5.repository.ArtistRepository;
import com.kdg.springprojt5.repository.SongRepository;
import com.kdg.springprojt5.repository.UserRepository;
import com.kdg.springprojt5.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SpringDataUserService implements UserService {
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public void deleteUser(Long id) {
        songRepository.findAll().stream()
                .filter(song -> song.getUserId().equals(id))
                .forEach(song -> songRepository.deleteById(song.getId()));
        albumRepository.findAll().stream()
                .filter(album -> album.getUserId().equals(id))
                .forEach(album -> albumRepository.deleteById(album.getId()));
        artistRepository.getAllArtists().stream()
                .filter(artist -> artist.getUser().getId().equals(id))
                .forEach(artist -> artistRepository.deleteById(artist.getId()));
        userRepository.deleteById(id);
    }
}
