package com.kdg.springprojt5.service;


import com.kdg.springprojt5.domain.Song;

import java.util.List;


public interface SongService {

    Song getSongById(Long id);

    List<Song> getAllSongs();

    List<Song> getSongsByAlbumId(Long albumId);

    Song saveSong(Song song);

    void deleteSong(Long id);

    void printSong(Long id);
}
