package com.kdg.springprojt5.controllers.api;


import com.kdg.springprojt5.domain.*;
import com.kdg.springprojt5.service.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@ActiveProfiles("test")
public class SongApiControllerTest {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private AlbumArtistService albumArtistService;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SongService songService;

    @BeforeEach
    void setUp() {
        userService.saveUser(new User("testuser", passwordEncoder.encode("password"), UserRole.ADMIN));
        Artist artist = artistService.saveArtist(new Artist("Test Artist", 12, 1L));
        Album album = albumService.saveAlbum(new Album("Test Album", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26), 1L), artist.getId());
        AlbumArtist albumArtist = new AlbumArtist(album, artist);
        albumArtistService.saveAlbumArtist(albumArtist);
    }

    @AfterEach
    public void tearDown() {
        songService.getAllSongs().forEach(song -> songService.deleteSong(song.getId()));
        albumArtistService.getAllAlbumArtists().forEach(albumArtist -> albumArtistService.deleteAlbumArtist(albumArtist));
        albumService.getAllAlbums().forEach(album -> albumService.deleteAlbum(album.getId()));
        artistService.getAllArtists().forEach(artist -> artistService.deleteArtist(artist.getId()));
    }


    @Test
    public void testDeleteSong() throws Exception {
        Song song = new Song(1L, "https://example.com/song.mp3", "Test Song", 1, 180000L, false, 1L);
        Song savedSong = songService.saveSong(song);
        mockMvc.perform(delete("/api/songs/" + savedSong.getId()))
                .andExpect(status().isOk());
        assertTrue(songService.getAllSongs().isEmpty());
    }


}
