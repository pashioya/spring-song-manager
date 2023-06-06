package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.domain.*;
import com.kdg.springprojt5.repository.AlbumArtistRepository;
import com.kdg.springprojt5.repository.AlbumRepository;
import com.kdg.springprojt5.repository.ArtistRepository;
import com.kdg.springprojt5.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc(addFilters = false)
public class AlbumApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumArtistRepository albumArtistRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        User testUser = new User("testuser", passwordEncoder.encode("password"), UserRole.ADMIN);
        userRepository.save(testUser);

        Artist artist = new Artist("Test Artist", 156, testUser.getId());
        artistRepository.save(artist);

        Album album = new Album("Test Album", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26), testUser.getId());
        albumRepository.save(album);

        albumArtistRepository.save(new AlbumArtist(album, artist));

        // Mock the authentication to return the test user
        Mockito.when(authentication.getPrincipal()).thenReturn(testUser);
    }

    @AfterEach
    void tearDown() {
        albumArtistRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getAlbumsShouldReturnAllAlbums() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/albums")
                        .principal(authentication))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
