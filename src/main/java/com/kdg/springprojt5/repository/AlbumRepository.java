package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.AlbumArtist;

import java.util.List;

public interface AlbumRepository {
    Album save(Album album);
    List<Album> getAllAlbums();
    Album getAlbumById(long id);
    List<Album> getAlbumsByArtistId(long id);
    void updateAlbumArtist(AlbumArtist albumArtist);
    void deleteAlbumArtist(AlbumArtist albumArtist);
    void deleteById(long id);

}
