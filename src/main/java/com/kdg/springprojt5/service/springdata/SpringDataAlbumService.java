package com.kdg.springprojt5.service.springdata;


import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.repository.springdata.*;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.util.JsonHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springData")
public class SpringDataAlbumService implements AlbumService {
    private final SpringDataAlbumRepository albumRepository;
    private final SpringDataArtistRepository artistRepository;
    private final SpringDataSongRepository songRepository;
    private final SpringDataAlbumArtistRepository albumArtistRepository;

    private final Logger logger;
    private final JsonHandler jsonHandler;


    public SpringDataAlbumService(SpringDataAlbumRepository albumRepository, SpringDataArtistRepository artistRepository, SpringDataSongRepository songRepository, SpringDataAlbumArtistRepository albumArtistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.albumArtistRepository = albumArtistRepository;
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
        this.jsonHandler = new JsonHandler();
    }

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        albums.forEach(album -> album.setSongs(songRepository.getSongsByAlbumId(album.getId())));
        albums.forEach(album -> album.setArtists(artistRepository.getAlbumsArtists(album.getId())));
        return albums;
    }

    @Override
    public Album getAlbumById(Long id) {
        Album album = albumRepository.getReferenceById(id);
        album.setSongs(songRepository.getSongsByAlbumId(id));
        album.setArtists(artistRepository.getAlbumsArtists(id));
        return album;
    }

    @Override
    public Album saveAlbum(Album album, Long artistId) {
        for(Album a : albumRepository.findAll()){
            if(a.equals(album)){
                return a;
            }
        }
        albumRepository.save(album);

        AlbumArtist albumArtist = new AlbumArtist(artistRepository.getArtistById(artistId),albumRepository.getReferenceById(album.getId()));
        albumArtistRepository.save(albumArtist);
        return album;
    }


    @Override
    public void deleteAlbum(Long id) {
        songRepository.getSongsByAlbumId(id).forEach(song -> songRepository.deleteById(song.getId()));
        albumArtistRepository.delete(new AlbumArtist(artistRepository.getArtistById(artistRepository.getAlbumsArtists(id).get(0).getId()), albumRepository.getReferenceById(id)));
        albumRepository.deleteById(id);
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

