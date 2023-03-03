package com.kdg.springprojt5.controllers.api.dto;

import java.util.List;

public class AlbumSongsDto {
    private String albumName;
    private List<SongDto> songs;

    public AlbumSongsDto(String albumName, List<SongDto> songs) {
        this.albumName = albumName;
        this.songs = songs;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }
}
