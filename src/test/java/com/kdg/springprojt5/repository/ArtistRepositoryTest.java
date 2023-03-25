package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;

    @Test
    public void testSaveArtistWithoutName() {
        Artist artist = new Artist();
        artistRepository.save(artist);

        List<Artist> artists = artistRepository.getAllArtists();
        assertFalse(artists.contains(artist));
    }

    @Test
    public void testSaveArtistWithName() {
        Artist artist = new Artist();
        artist.setArtistName("Test");
        artistRepository.save(artist);

        List<Artist> artists = artistRepository.getAllArtists();
        assertTrue(artists.contains(artist));
    }
}
