package com.kdg.springprojt5.controllers.api;


import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.service.SongService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@ActiveProfiles("test")
public class SongApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SongService songService;

    @Test
    public void testDeleteSong() throws Exception {
        Song song = new Song();
        song.setAlbumId(1L);
        song.setUrl("https://example.com/song.mp3");
        song.setSongTitle("Test Song");
        song.setTrackNumber(1);
        song.setDurationMS(180000L);
        song.setExplicit(false);
        song.setUserId(1L);
        Song savedSong = songService.saveSong(song);

        // Send a DELETE request to the API to delete the song
        mockMvc.perform(delete("/api/song/" + savedSong.getId() + "/delete"))
                .andExpect(status().isOk());

        // Verify that the song was deleted
        assertNull(songService.getSongById(savedSong.getId()));
    }

}
