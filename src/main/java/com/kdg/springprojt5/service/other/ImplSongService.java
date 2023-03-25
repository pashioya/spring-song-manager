package com.kdg.springprojt5.service.other;


import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.repository.SongRepository;
import com.kdg.springprojt5.service.SongService;
import com.kdg.springprojt5.util.JsonHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Profile("!springData")
public class ImplSongService implements SongService {
    private final Logger logger;
    private final SongRepository songRepository;
    private final JsonHandler jsonHandler;


    public ImplSongService(SongRepository songRepository) {
        this.songRepository = songRepository;
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.jsonHandler = new JsonHandler();
    }

    @Override
    public Song getSongById(Long id) {
        return songRepository.getSongById(id);
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.getAllSongs();
    }

    @Override
    public List<Song> getSongsByAlbumId(Long albumId) {
        return songRepository.getSongsByAlbumId(albumId);
    }


    @Override
    public Song saveSong(Song song) {
        for (Song s: songRepository.getAllSongs()) {
            if (s.equals(song)) {
                logger.debug("Song already exists");
                return s;
            }
        }
        logger.info("Song does not exist, adding song");
        songRepository.save(song);
        return song;
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public void printSong(Long id) {
        logger.info(jsonHandler.saveToJson(songRepository.getSongById(id),songRepository.getSongById(id).getSongTitle()));
    }
}
