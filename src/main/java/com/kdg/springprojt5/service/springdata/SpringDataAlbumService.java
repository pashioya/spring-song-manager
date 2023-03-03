package com.kdg.springprojt5.service.springdata;


import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.repository.springdata.SpringDataAlbumArtistRepository;
import com.kdg.springprojt5.repository.springdata.SpringDataAlbumRepository;
import com.kdg.springprojt5.repository.springdata.SpringDataArtistRepository;
import com.kdg.springprojt5.repository.springdata.SpringDataSongRepository;
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


    public SpringDataAlbumService(SpringDataAlbumRepository albumRepository, SpringDataArtistRepository artistRepository, SpringDataSongRepository songRepository, SpringDataAlbumArtistRepository albumArtistRepository, JsonHandler jsonHandler) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.albumArtistRepository = albumArtistRepository;
        this.jsonHandler = jsonHandler;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }


    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        albums.forEach(album -> album.setSongs(songRepository.getSongsByAlbumId(album.getId())));
        albums.forEach(album -> album.setArtists(artistRepository.getAlbumsArtists(album.getId())));
        return albumRepository.getAllAlbums();
    }

    @Override
    public Album getAlbumById(long id) {
        Album album = albumRepository.getAlbumById(id);
        album.setSongs(songRepository.getSongsByAlbumId(id));
        album.setArtists(artistRepository.getAlbumsArtists(id));
        return album;
    }

    @Override
    public Album saveAlbum(Album album, long artistId) {
        for(Album a : albumRepository.findAll()){
            if(a.equals(album)){
                return a;
            }
        }
        albumRepository.save(album);

        AlbumArtist albumArtist = new AlbumArtist(artistRepository.getArtistById(artistId),albumRepository.getAlbumById(album.getId()));
        albumArtistRepository.save(albumArtist);
        return album;
    }


    @Override
    public void deleteAlbum(long id) {
        songRepository.getSongsByAlbumId(id).forEach(song -> songRepository.deleteById(song.getId()));
        albumRepository.deleteById(id);
    }

    @Override
    public void printAlbum(long id) {

        Album album = albumRepository.getAlbumById((id));
        assert album != null;
        String filename = album.getAlbumName();
        String json = jsonHandler.saveToJson(album, filename);
        logger.info("Album saved to JSON file: " + filename);
        logger.info(json);
    }
}

