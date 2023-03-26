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
        Song song = new Song( 1L, "https://example.com/song.mp3", "Test Song", 1, 180000L, false, 1L);
        Song savedSong = songService.saveSong(song);

        mockMvc.perform(delete("/api/song/" + savedSong.getId() + "/delete"))
                .andExpect(status().isOk());

        assertNull(songService.getSongById(savedSong.getId()));
    }

}
