package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.Song;

import java.util.List;

public interface SongRepository {
    Song save(Song song);
    List<Song> getAllSongs();
    Song getSongById(long id);
    List<Song> getSongsByAlbumId(long id);
    void deleteById(long id);
}
