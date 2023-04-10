package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.NewUserDto;
import com.kdg.springprojt5.controllers.api.dto.UserDto;
import com.kdg.springprojt5.domain.User;
import com.kdg.springprojt5.domain.UserRole;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import com.kdg.springprojt5.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;
    private final AlbumService albumService;
    private final SongService songService;
    private final ArtistService artistService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Logger logger;

    private final ModelMapper modelMapper;


    public UserApiController(UserService userService, AlbumService albumService, SongService songService, ArtistService artistService, BCryptPasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userService = userService;
        this.albumService = albumService;
        this.songService = songService;
        this.artistService = artistService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserDto user = modelMapper.map(userService.getUserById(id), UserDto.class);
        logger.info("User with id: " + id + " was found");
        if (user == null) {
            logger.info("User with id: " + id + " was not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable("id") Long id, @Valid @ModelAttribute NewUserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(UserRole.valueOf(userDto.getRole()));
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @ModelAttribute NewUserDto newUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.saveUser(modelMapper.map(newUserDto, User.class));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        artistService.getAllArtists().stream()
                .filter(artist -> artist.getUser().getId().equals(id))
                .forEach(artist -> artistService.deleteArtist(artist.getId()));

        albumService.getAllAlbums().stream()
                .filter(album -> album.getUser().getId().equals(id))
                .forEach(album -> albumService.deleteAlbum(album.getId()));

        songService.getAllSongs().stream()
                .filter(song -> song.getUser().getId().equals(id))
                .forEach(song -> songService.deleteSong(song.getId()));

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
