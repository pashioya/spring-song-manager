package com.kdg.springprojt5.service.other;


import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.repository.AlbumRepository;
import com.kdg.springprojt5.repository.ArtistRepository;
import com.kdg.springprojt5.repository.SongRepository;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.util.JsonHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("!springData")
public class ImplAlbumService implements AlbumService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final Logger logger;
    private final JsonHandler jsonHandler;


    public ImplAlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository, SongRepository songRepository, JsonHandler jsonHandler) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.jsonHandler = jsonHandler;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }


    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = albumRepository.getAllAlbums();
        albums.forEach(album -> album.setSongs(songRepository.getSongsByAlbumId(album.getId())));
        albums.forEach(album -> album.setArtists(artistRepository.getAlbumsArtists(album.getId())));
        return albums;
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
        for(Album a : albumRepository.getAllAlbums()){
            if(a.equals(album)){
                return a;
            }
        }
        albumRepository.save(album);
        albumRepository.updateAlbumArtist(new AlbumArtist(artistRepository.getArtistById(artistId), albumRepository.getAlbumById(album.getId())));
        return album;
    }

    @Override
    public void deleteAlbum(long id) {
        songRepository.getSongsByAlbumId(id).forEach(song -> songRepository.deleteById(song.getId()));
        albumRepository.deleteAlbumArtist(new AlbumArtist(artistRepository.getArtistById(artistRepository.getAlbumsArtists(id).get(0).getId()), albumRepository.getAlbumById(id)));
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

