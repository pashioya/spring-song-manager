package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.domain.*;
import com.kdg.springprojt5.repository.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.LinkedList;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class AlbumControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumArtistRepository albumArtistRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        User testUser = new User("testuser", passwordEncoder.encode("password"), UserRole.ADMIN);
        userRepository.save(testUser);

        Artist artist = new Artist("Test Artist", 156, testUser.getId());
        artistRepository.save(artist);

        Album album = new Album("Test Album", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26), testUser.getId());
        albumRepository.save(album);

        albumArtistRepository.save(new AlbumArtist(album, artist));
        Song song = new Song(1L, "https://www.youtube.com/watch?v=1", "Test Song", 1, 180000L, false, testUser.getId());
        songRepository.save(song);
    }

    @Test
    void fullAlbumShouldLoadFullAlbumPageIncludingCorrectAlbum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/album/{id}", 1L)
                        .sessionAttr("history", new LinkedList<String>()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("album"))
                .andExpect(MockMvcResultMatchers.model().attribute("title", "fullAlbum"))
                .andExpect(MockMvcResultMatchers.model().attribute("headerList", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.model().attribute("footerList", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.view().name("fullAlbum"));
    }

    @AfterEach
    void tearDown() {
        songRepository.deleteAll();
        albumArtistRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        userRepository.deleteAll();
    }
}
