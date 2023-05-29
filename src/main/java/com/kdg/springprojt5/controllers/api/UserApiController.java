package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.NewUserDto;
import com.kdg.springprojt5.controllers.api.dto.UpdateUserDto;
import com.kdg.springprojt5.controllers.api.dto.UserDto;
import com.kdg.springprojt5.domain.User;
import com.kdg.springprojt5.domain.UserRole;
import com.kdg.springprojt5.security.AdminOnly;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import com.kdg.springprojt5.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;
    private final AlbumService albumService;
    private final SongService songService;
    private final ArtistService artistService;

    private final Logger logger = LoggerFactory.getLogger(UserApiController.class);

    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User> users = userService.getAllUsers();
            if (users == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            UserDto userDto = modelMapper.map(user, UserDto.class);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @AdminOnly
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            System.out.println(userDto);
            user.setUsername(userDto.getUsername());
            user.setRole(UserRole.valueOf(userDto.getRole()));
            userService.saveUser(user);
            return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody NewUserDto newUserDto,
                                           BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            User user = modelMapper.map(newUserDto, User.class);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            userService.saveUser(user);
            return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @AdminOnly
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            artistService.getAllArtists().stream()
                    .filter(artist -> artist.getUser().getId().equals(id))
                    .forEach(artist -> artistService.deleteArtist(artist.getId()));

            albumService.getAllAlbums().stream()
                    .filter(album -> album.getUserId().equals(id))
                    .forEach(album -> albumService.deleteAlbum(album.getId()));

            songService.getAllSongs().stream()
                    .filter(song -> song.getUserId().equals(id))
                    .forEach(song -> songService.deleteSong(song.getId()));

            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
