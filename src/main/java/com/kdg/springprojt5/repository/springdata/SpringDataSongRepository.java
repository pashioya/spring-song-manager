package com.kdg.springprojt5.repository.springdata;

import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.repository.SongRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Profile("springData")
public interface SpringDataSongRepository extends JpaRepository<Song, Long> {

//    @NotNull
//    Song save(@NotNull Song song);
//    Song getSongById(long id);
//    @NonNull
//    List<Song> findAll();
//
//    @Query("SELECT a FROM Song a")
//    List<Song> getAllSongs();
    List<Song> getSongsByAlbumId(long id);
//    void deleteById(long id);

}

