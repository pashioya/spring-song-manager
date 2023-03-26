package com.kdg.springprojt5.repository;


import com.kdg.springprojt5.domain.Song;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;
    @Test
    public void testNullabilityConstraints() {
        // create a song with a null url
        Song song = new Song(1L, null, "Song 1", 1, 180000L, false, 1L);

        // attempt to persist the song (should fail due to nullability constraint)
        assertThrows(DataIntegrityViolationException.class, () -> songRepository.save(song));
    }

}