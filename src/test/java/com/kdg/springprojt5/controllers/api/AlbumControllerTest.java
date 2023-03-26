package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.domain.StatusEnum;
import com.kdg.springprojt5.repository.AlbumRepository;
import com.kdg.springprojt5.repository.SongRepository;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumControllerTest {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @BeforeAll
    public void setup() {
        // Create a new album
        Album album = new Album("Test Album", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26),2L);

//        create a mew song
        Song song = new Song(1L, "https://www.youtube.com/watch?v=1", "Song 1", 1, 180000L, false, 1L);

//        add song to album
        albumRepository.save(album);
        songRepository.save(song);
    }

    @AfterAll
    public void cleanup() {
        // Delete the album
        albumRepository.deleteById(1L);
    }

    @Test
    public void testEagerLoading() {
        // Retrieve the album using eager loading
        Album album = albumRepository.getAlbumById(1L);

        // Assert that the album's songs were eagerly loaded
        assertTrue(Hibernate.isInitialized(album.getSongs()));

    }

    @Test
    public void testLazyLoading() {
        // Retrieve the album using lazy loading
        Album album = albumRepository.getAlbumById(1L);

        // Assert that the album's songs were lazily loaded
        assertFalse(Hibernate.isInitialized(album.getSongs()));

        // Load the album's songs
        Hibernate.initialize(album.getSongs());

        // Assert that the album's songs were loaded
        assertTrue(Hibernate.isInitialized(album.getSongs()));
    }

}
