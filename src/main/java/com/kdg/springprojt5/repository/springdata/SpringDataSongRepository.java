package com.kdg.springprojt5.repository.springdata;

import com.kdg.springprojt5.domain.Song;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Profile("springData")
public interface SpringDataSongRepository extends JpaRepository<Song, Long> {
    List<Song> getSongsByAlbumId(Long id);
}

