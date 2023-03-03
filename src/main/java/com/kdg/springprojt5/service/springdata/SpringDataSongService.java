package com.kdg.springprojt5.service.springdata;


import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.repository.springdata.SpringDataSongRepository;
import com.kdg.springprojt5.service.SongService;
import com.kdg.springprojt5.util.JsonHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Profile("springData")
public class SpringDataSongService implements SongService {
    private final Logger logger;
    private final SpringDataSongRepository songRepository;
    private final JsonHandler jsonHandler;


    public SpringDataSongService(SpringDataSongRepository songRepository) {
        this.songRepository = songRepository;
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.jsonHandler = new JsonHandler();
    }

    @Override
    public Song getSongById(long id) {
        return songRepository.getSongById(id);
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> getSongsByAlbumId(long albumId) {
        return songRepository.getSongsByAlbumId(albumId);
    }


    @Override
    public Song saveSong(Song song) {
        for (Song s: songRepository.findAll()) {
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
    public void deleteSong(long id) {
        songRepository.deleteById(id);
    }

    @Override
    public void printSong(long id) {
        logger.info(jsonHandler.saveToJson(songRepository.getSongById(id),songRepository.getSongById(id).getSongTitle()));
    }
}
