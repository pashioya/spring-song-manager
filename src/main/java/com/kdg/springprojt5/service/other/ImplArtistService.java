package com.kdg.springprojt5.service.other;

import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.repository.AlbumRepository;
import com.kdg.springprojt5.repository.ArtistRepository;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.util.JsonHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("!springData")
public class ImplArtistService implements ArtistService {
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final JsonHandler jsonHandler;

    private final Logger logger;

    public ImplArtistService( ArtistRepository artistRepository, AlbumRepository albumRepository, JsonHandler jsonHandler) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.jsonHandler = jsonHandler;
        this.logger = LoggerFactory.getLogger(ImplArtistService.class);
    }
    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artists = artistRepository.getAllArtists();
        artists.forEach(artist -> artist.setAlbums(albumRepository.getAlbumsByArtistId(artist.getId())));
        return artists;
    }

    @Override
    public Artist saveArtist(Artist artist) {
        artistRepository.save(artist);
        return artist;
    }

    @Override
    public Artist getArtistById(Long id) {
        Artist artist = artistRepository.getArtistById(id);
        if (artist == null) return null;
        artist.setAlbums(albumRepository.getAlbumsByArtistId(id));
        artist.calcFavoriteGenre();
        return artist;
    }

    @Override
    public void deleteArtist(Long id) {
        albumRepository.getAlbumsByArtistId(id).forEach(album -> albumRepository.deleteAlbumArtist(new AlbumArtist(artistRepository.getArtistById(id),album)));
        albumRepository.getAlbumsByArtistId(id).forEach(album -> albumRepository.deleteById(album.getId()));
        artistRepository.deleteById(id);
    }

    @Override
    public void printArtist(Long id) {
        jsonHandler.saveToJson(getArtistById(id),getArtistById(id).getArtistName());
        logger.info("Artist saved to json");
    }

    @Override
    public void addArtistToAlbum(Artist artist, Long albumId) {
        artistRepository.save(artist);
        artistRepository.updateAlbumArtist(new AlbumArtist(artist, albumRepository.getAlbumById(albumId)));
    }

    @Override
    public List<Artist> getAllArtistsForAlbum(Long albumId) {
        return artistRepository.getAllArtistsForAlbum(albumId);
    }
}
