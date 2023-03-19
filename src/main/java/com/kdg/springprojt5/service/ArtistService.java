package com.kdg.springprojt5.service;


import com.kdg.springprojt5.domain.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> getAllArtists();
    Artist saveArtist(Artist artist);
    Artist getArtistById(long id);
    void deleteArtist(long id);
    void printArtist(long id);
    void addArtistToAlbum(Artist artist, long albumId);

    List<Artist> getAllArtistsForAlbum(Long albumId);
}
