package com.kdg.springprojt5.service;

import com.kdg.springprojt5.domain.AlbumArtist;

import java.util.List;

public interface AlbumArtistService {
    void saveAlbumArtist(AlbumArtist albumArtist);

    void deleteAlbumArtist(AlbumArtist albumArtist);

    List<AlbumArtist> getAllAlbumArtists();
}
