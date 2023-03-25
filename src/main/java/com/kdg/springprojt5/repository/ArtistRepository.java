package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.domain.Artist;

import java.util.List;

public interface ArtistRepository {
    Artist save(Artist artist);
    List<Artist> getAllArtists();
    Artist getArtistById(Long id);
    List<Artist> getAlbumsArtists(Long id);
    void updateAlbumArtist(AlbumArtist albumArtist);
    void deleteById(Long id);

    List<Artist> getAllArtistsForAlbum(Long albumId);
}
