package com.kdg.springprojt5.service.springdata;


import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.repository.AlbumRepository;
import com.kdg.springprojt5.repository.ArtistRepository;
import com.kdg.springprojt5.repository.SongRepository;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.util.JsonHandler;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpringDataAlbumService implements AlbumService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final JsonHandler jsonHandler;


    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        albums.forEach(album -> album.setSongs(songRepository.getSongsByAlbumId(album.getId())));
        albums.forEach(album -> album.setArtists(artistRepository.getAlbumsArtists(album.getId())));
        return albums;
    }

    @Override
    public Album getAlbumById(Long id) {
        return albumRepository.getReferenceById(id);
    }

    @Override
    public Album saveAlbum(Album album, Long artistId) {
        for (Album a : albumRepository.findAll()) {
            if (a.equals(album)) {
                return a;
            }
        }
        albumRepository.save(album);
        return album;
    }


    @Override
    public void deleteAlbum(Long id) {
        songRepository.getSongsByAlbumId(id).forEach(song -> songRepository.deleteById(song.getId()));
    }

    @Override
    public void printAlbum(Long id) {
        Album album = albumRepository.getReferenceById((id));
        String filename = album.getAlbumName();
        String json = jsonHandler.saveToJson(album, filename);
        logger.info("Album saved to JSON file: " + filename);
        logger.info(json);
    }
}

