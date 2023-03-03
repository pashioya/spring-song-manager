package com.kdg.springprojt5.service.springdata;

import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.repository.springdata.SpringDataAlbumRepository;
import com.kdg.springprojt5.repository.springdata.SpringDataArtistRepository;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.util.JsonHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springData")
public class SpringDataArtistService implements ArtistService {
    private final SpringDataArtistRepository artistRepository;
    private final SpringDataAlbumRepository albumRepository;
    private final JsonHandler jsonHandler;

    private final Logger logger;

    public SpringDataArtistService(SpringDataArtistRepository artistRepository, SpringDataAlbumRepository albumRepository, JsonHandler jsonHandler) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.jsonHandler = jsonHandler;
        this.logger = LoggerFactory.getLogger(SpringDataArtistService.class);
    }
    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        artists.forEach(artist -> artist.setAlbums(albumRepository.getAlbumsByArtistId(artist.getId())));
        return artists;
    }

    @Override
    public Artist saveArtist(Artist artist) {
        artistRepository.save(artist);
        return artist;
    }

    @Override
    public Artist getArtistById(long id) {
        Artist artist = artistRepository.getArtistById(id);
        artist.setAlbums(albumRepository.getAlbumsByArtistId(id));
        return artist;
    }

    @Override
    public void deleteArtist(long id) {
        artistRepository.deleteById(id);
    }

    @Override
    public void printArtist(long id) {
        jsonHandler.saveToJson(getArtistById(id),getArtistById(id).getArtistName());
        logger.info("Artist saved to json");
    }

    @Override
    public void addArtistToAlbum(Artist artist, long albumId) {
        artistRepository.save(artist);
        artistRepository.updateAlbumArtist(new AlbumArtist(artist, albumRepository.getAlbumById(albumId)));
    }
}
