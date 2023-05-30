package com.kdg.springprojt5.service.springdata;


import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.repository.SongRepository;
import com.kdg.springprojt5.service.SongService;
import com.kdg.springprojt5.util.JsonHandler;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpringDataSongService implements SongService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final SongRepository songRepository;
    private final JsonHandler jsonHandler;


    @Override
    public Song getSongById(Long id) {
        return songRepository.getReferenceById(id);
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> getSongsByAlbumId(Long albumId) {
        return songRepository.getSongsByAlbumId(albumId);
    }


    @Override
    public Song saveSong(Song song) {
        logger.info("Song does not exist, adding song");
        songRepository.save(song);
        return song;
    }

    @Override
    public List<Song> getSongsByTitle(String title) {
        List<Song> songs = songRepository.findAll();
        songs.removeIf(song -> !song.getSongTitle().toLowerCase().contains(title.toLowerCase()));
        return songs;
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public void printSong(Long id) {
        logger.info(jsonHandler.saveToJson(songRepository.getReferenceById(id), songRepository.getReferenceById(id).getSongTitle()));
    }
}
