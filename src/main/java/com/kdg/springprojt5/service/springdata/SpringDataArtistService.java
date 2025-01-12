package com.kdg.springprojt5.service.springdata;

import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.repository.AlbumArtistRepository;
import com.kdg.springprojt5.repository.AlbumRepository;
import com.kdg.springprojt5.repository.ArtistRepository;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.util.JsonHandler;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpringDataArtistService implements ArtistService {
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final AlbumArtistRepository albumArtistRepository;
    private final JsonHandler jsonHandler;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

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
    public Artist getArtistById(Long id) {
        return artistRepository.getArtistById(id);
    }

    @Override
    public void deleteArtist(Long id) {
        albumRepository.getAlbumsByArtistId(id).forEach(album -> {
            if ((long) album.getArtists().size() == 1) {
                albumRepository.deleteById(album.getId());
                albumArtistRepository.deleteAlbumArtistByArtistId(id);
            }
        });
        artistRepository.deleteById(id);
    }

    @Override
    public void printArtist(Long id) {
        Artist artist = getArtistById(id);
        jsonHandler.saveToJson(artist, artist.getArtistName());
        logger.info("Artist saved to json");
    }

    @Override
    public List<Artist> getAllArtistsForAlbum(Long albumId) {
        var artists = artistRepository.getAlbumsArtists(albumId);
        artists.forEach(artist -> artist.setAlbums(albumRepository.getAlbumsByArtistId(artist.getId())));
        return artists;
    }
}
