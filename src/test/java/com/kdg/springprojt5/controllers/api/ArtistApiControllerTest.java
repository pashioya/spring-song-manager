package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.domain.*;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import com.kdg.springprojt5.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc(addFilters = false)
public class ArtistApiControllerTest {
    @Mock
    private ArtistService artistService;

    @Mock
    private AlbumService albumService;

    @Mock
    private SongService songService;

    @Mock
    private UserService userService;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private Authentication authentication;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        User testUser = new User("testuser", passwordEncoder.encode("password"), UserRole.ADMIN);
        userService.saveUser(testUser);

        Artist artist = new Artist("Test Artist", 156, testUser.getId());
        artistService.saveArtist(artist);

        Album album = new Album("Test Album", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26), testUser.getId());
        albumService.saveAlbum(album, artist.getId());

        Song song = new Song(1L, "https://www.youtube.com/watch?v=1", "Test Song", 1, 180000L, false, testUser.getId());
        songService.saveSong(song);

        // Mock the authentication to return the test user
        Mockito.when(authentication.getPrincipal()).thenReturn(testUser);
    }

    @AfterAll
    void tearDown() {
        songService.deleteSong(1L);
        albumService.deleteAlbum(1L);
        artistService.deleteArtist(1L);
        userService.deleteUser(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteArtistShouldReturnNoContent() throws Exception {
        // Mock artist and related albums
        Artist artist = new Artist();
        artist.setAlbums(new ArrayList<>());
        Album album1 = new Album();
        album1.setId(1L);
        Album album2 = new Album();
        album2.setId(2L);
        artist.getAlbums().add(album1);
        artist.getAlbums().add(album2);

        // Mock service methods
        when(artistService.getArtistById(anyLong())).thenReturn(artist);
        doNothing().when(songService).deleteSong(anyLong());
        doNothing().when(artistService).deleteArtist(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/artists/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Verify interactions
        verify(artistService, times(1)).getArtistById(1L);
        verify(albumService, times(2)).deleteAlbum(anyLong());
        verify(songService, times(2)).deleteSong(anyLong());
        verify(artistService, times(1)).deleteArtist(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteArtistShouldReturnNotFoundWhenArtistNotFound() throws Exception {
        // Mock service method to return null
        when(artistService.getArtistById(anyLong())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/artists/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Verify interactions
        verify(artistService, times(1)).getArtistById(1L);
        verify(albumService, never()).deleteAlbum(anyLong());
        verify(songService, never()).deleteSong(anyLong());
        verify(artistService, never()).deleteArtist(anyLong());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteArtistShouldReturnNotFoundWhenNoAlbums() throws Exception {
        // Mock artist with no albums
        Artist artist = new Artist();
        artist.setAlbums(null);

        // Mock service method to return artist with no albums
        when(artistService.getArtistById(anyLong())).thenReturn(artist);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/artists/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // Verify interactions
        verify(artistService, times(1)).getArtistById(1L);
        verify(albumService, never()).deleteAlbum(anyLong());
        verify(songService, never()).deleteSong(anyLong());
        verify(artistService, never()).deleteArtist(anyLong());
    }
}
