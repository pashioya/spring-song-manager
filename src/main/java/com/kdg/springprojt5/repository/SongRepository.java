package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.Song;

import java.util.List;

public interface SongRepository {
    Song save(Song song);
    List<Song> getAllSongs();
    Song getSongById(Long id);
    List<Song> getSongsByAlbumId(Long id);
    void deleteById(Long id);
}
