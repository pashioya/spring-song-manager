package com.kdg.springprojt5.service.springdata;

import com.kdg.springprojt5.domain.*;
import com.kdg.springprojt5.repository.springdata.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@SpringBootTest
class SpringDataSongServiceTest {

    @Autowired
    private SpringDataUserRepository userRepository;

    @Autowired
    private SpringDataArtistRepository artistRepository;

    @Autowired
    private SpringDataAlbumRepository albumRepository;

    @Autowired
    private SpringDataSongRepository songRepository;

    @Autowired
    private SpringDataAlbumArtistRepository albumArtistRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        User testUser = new User("Lars", passwordEncoder.encode("lars"), UserRole.USER);
        Artist testArtist = new Artist("test Artist",156,1L);
        Album testAlbum = new Album("Test Album", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26),2L);
        Song testSong = new Song(1L, "https://www.youtube.com/watch?v=1", "Test Song", 1, 180000L, false, 1L);

        userRepository.save(testUser);
        artistRepository.save(testArtist);
        albumArtistRepository.save(new AlbumArtist(testArtist, testAlbum));
        albumRepository.save(testAlbum);
        songRepository.save(testSong);
    }

    @AfterEach
    void tearDown() {
        songRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void saveSong() {
        // Assert that the song was saved successfully
        Song savedSong = songRepository.getReferenceById(2L);
        assertNotNull(savedSong);
        assertEquals("Song Title is the correct ", "Test Song", savedSong.getSongTitle());
        assertEquals("Track Number is Correct",1, savedSong.getTrackNumber());
        assertEquals("Duration is Correct",180000L, savedSong.getDurationMS());
        assertEquals("Explicit is Correct",false, savedSong.isExplicit());
        assertEquals("Album Id is Correct",1L, savedSong.getAlbum().getId());
        assertEquals("Creator Is Correct","Lars", savedSong.getUser().getUsername());
    }

    @Test
    void getAllSongs(){
//        assert that the number of songs is correct
        assertEquals("Number of songs is correct", 1, songRepository.findAll().size());
    }
}