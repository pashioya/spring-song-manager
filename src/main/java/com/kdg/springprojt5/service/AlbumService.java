package com.kdg.springprojt5.service;

import com.kdg.springprojt5.domain.Album;
import java.util.List;


public interface AlbumService {
    List<Album> getAllAlbums();
    Album getAlbumById(long id);

    Album saveAlbum(Album album, long artistId);

    void deleteAlbum(long id);

    void printAlbum(long id);



}
