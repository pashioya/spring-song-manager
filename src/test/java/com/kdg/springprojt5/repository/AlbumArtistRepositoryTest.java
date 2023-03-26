package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.domain.StatusEnum;
import com.kdg.springprojt5.repository.springdata.SpringDataAlbumArtistRepository;
import com.kdg.springprojt5.repository.springdata.SpringDataAlbumRepository;
import com.kdg.springprojt5.repository.springdata.SpringDataArtistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumArtistRepositoryTest {
    @Autowired
    private SpringDataArtistRepository artistRepository;

    @Autowired
    private SpringDataAlbumRepository albumRepository;

    @Autowired
    private SpringDataAlbumArtistRepository albumArtistRepository;
    @BeforeEach
    public void setUp() {
        Artist artist = new Artist("Test Artist", 156, 1L);
        Album album = new Album("Test Album", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26), 2L);

        artistRepository.save(artist);
        albumRepository.save(album);

        AlbumArtist albumArtist = new AlbumArtist(artist, album);
        albumArtistRepository.save(albumArtist);
    }

    @Test
    public void testLazyLoading() {
        // Retrieving AlbumArtist without explicitly fetching artist and album
        AlbumArtist albumArtist = albumArtistRepository.findById(1L).orElse(null);
        assert albumArtist != null;
        Assertions.assertNull(albumArtist.getArtist());
        Assertions.assertNull(albumArtist.getAlbum());
    }
}
