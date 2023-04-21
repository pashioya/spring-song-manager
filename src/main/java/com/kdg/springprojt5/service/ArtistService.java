package com.kdg.springprojt5.service;


import com.kdg.springprojt5.domain.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> getAllArtists();

    Artist saveArtist(Artist artist);

    Artist getArtistById(Long id);

    void deleteArtist(Long id);

    void printArtist(Long id);

    List<Artist> getAllArtistsForAlbum(Long albumId);
}
