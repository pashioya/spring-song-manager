package com.kdg.springprojt5.service;


import com.kdg.springprojt5.domain.Song;

import java.util.List;


public interface SongService {

    Song getSongById(long id);

    List<Song> getAllSongs();
    List<Song> getSongsByAlbumId(long albumId);

    Song saveSong(Song song);

    void deleteSong(long id);

    void printSong(long id);
}
