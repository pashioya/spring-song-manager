package com.kdg.springprojt5.service;

import com.kdg.springprojt5.domain.*;
import com.kdg.springprojt5.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
class SongServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumArtistRepository albumArtistRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SongService songService;

    @BeforeEach
    void setUp() {
        User testUser = new User("Lars", passwordEncoder.encode("lars"), UserRole.ADMIN);
        Artist testArtist = new Artist("testArtist", 156, 1L);
        Album testAlbum = new Album("Test Album", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26), 1L);
        Song testSong = new Song(1L, "https://www.youtube.com/watch?v=1", "Test Song", 1, 180000L, false, 1L);
        Song testSong2 = new Song(1L, "https://www.youtube.com/watch?v=1", "Test Song 2", 1, 180000L, false, 1L);
        Song testSong3 = new Song(1L, "https://www.youtube.com/watch?v=1", "Test Song 3", 1, 180000L, false, 1L);
        Song testSong4 = new Song(1L, "https://www.youtube.com/watch?v=1", "Song 4", 1, 180000L, false, 1L);
        Song testSong5 = new Song(1L, "https://www.youtube.com/watch?v=1", " Song 5", 1, 180000L, false, 1L);


        userRepository.save(testUser);
        artistRepository.save(testArtist);
        albumRepository.save(testAlbum);
        albumArtistRepository.save(new AlbumArtist(testAlbum, testArtist));
        songRepository.save(testSong);
        songRepository.save(testSong2);
        songRepository.save(testSong3);
        songRepository.save(testSong4);
        songRepository.save(testSong5);
    }

    @AfterEach
    void tearDown() {
        songRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getSongByTitle() {
        String title = "Test";
        // Act
        List<Song> matchingSongs = songService.getSongsByTitle(title);
        // Assert
        Assertions.assertEquals(3, matchingSongs.size());
        System.out.println(matchingSongs);
    }
}