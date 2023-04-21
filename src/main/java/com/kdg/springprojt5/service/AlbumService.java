package com.kdg.springprojt5.service;

import com.kdg.springprojt5.domain.Album;

import java.util.List;


public interface AlbumService {
    List<Album> getAllAlbums();

    Album getAlbumById(Long id);

    Album saveAlbum(Album album, Long artistId);

    void deleteAlbum(Long id);

    void printAlbum(Long id);

}
