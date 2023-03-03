package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.domain.Artist;

import java.util.List;

public interface ArtistRepository {
    Artist save(Artist artist);
    List<Artist> getAllArtists();
    Artist getArtistById(long id);
    List<Artist> getAlbumsArtists(long id);
    void updateAlbumArtist(AlbumArtist albumArtist);
    void deleteAlbumArtist(AlbumArtist albumArtist);
    void deleteById(long id);
}
