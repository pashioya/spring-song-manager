package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.AlbumArtist;

import java.util.List;

public interface AlbumRepository {
    Album save(Album album);
    List<Album> getAllAlbums();
    Album getAlbumById(Long id);
    List<Album> getAlbumsByArtistId(Long id);
    void updateAlbumArtist(AlbumArtist albumArtist);
    void deleteAlbumArtist(AlbumArtist albumArtist);
    void deleteById(Long id);

}
