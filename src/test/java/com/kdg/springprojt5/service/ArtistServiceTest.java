package com.kdg.springprojt5.service;

import com.kdg.springprojt5.domain.*;
import com.kdg.springprojt5.repository.AlbumArtistRepository;
import com.kdg.springprojt5.repository.AlbumRepository;
import com.kdg.springprojt5.repository.ArtistRepository;
import com.kdg.springprojt5.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class ArtistServiceTest {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AlbumArtistRepository albumArtistRepository;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userRepository.save(new User(
                "testuser",
                passwordEncoder.encode("password"),
                UserRole.USER
        ));

        artistRepository.save(new Artist(
                "Test Artist",
                156,
                1L
        ));

        artistRepository.save(new Artist(
                "Test Artist 2",
                156,
                1L
        ));

        albumRepository.save(new Album(
                "Test Album",
                12,
                StatusEnum.SINGLE,
                "Rock",
                LocalDate.of(1969, 9, 26),
                1L
        ));

        albumArtistRepository.save(
                new AlbumArtist(
                        albumRepository.getReferenceById(1L),
                        artistRepository.getReferenceById(1L)
                ));

        albumArtistRepository.save(
                new AlbumArtist(
                        albumRepository.getReferenceById(1L),
                        artistRepository.getReferenceById(2L)
                )
        );
    }

    @AfterEach
    public void tearDown() {
        albumArtistRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testGetAllArtistsForAlbum() {
        // Create test data
        Long albumId = 1L;
        Artist artist1 = artistRepository.findAll().get(0);
        Artist artist2 = artistRepository.findAll().get(1);
        List<Artist> artists = List.of(artist1, artist2);

        Album album = albumRepository.findAll().get(0);
        album.setArtists(artists);

        // Call the method
        List<Artist> result = artistService.getAllArtistsForAlbum(albumId);

        // Assert the result
        assertEquals(2, result.size());
        assertTrue(result.stream().map(Artist::getArtistName).anyMatch("Test Artist"::equals));
        assertTrue(result.stream().map(Artist::getArtistName).anyMatch("Test Artist 2"::equals));
    }
}
