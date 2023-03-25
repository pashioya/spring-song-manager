package com.kdg.springprojt5.controllers.mvc.viewmodel;


import com.kdg.springprojt5.domain.Album;

import java.util.List;

public class ArtistViewModel {
    private Long id;
    private String artistName;
    private double artistFollowers;
    private String favoriteGenre;
    private String user;
    private List<Album> albums;

    public ArtistViewModel(Long id, String artistName, double artistFollowers, String favoriteGenre, String  user, List<Album> albums) {
        this.id = id;
        this.artistName = artistName;
        this.artistFollowers = artistFollowers;
        this.favoriteGenre = favoriteGenre;
        this.user = user;
        this.albums = albums;
    }

    public ArtistViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public double getArtistFollowers() {
        return artistFollowers;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setArtistFollowers(double artistFollowers) {
        this.artistFollowers = artistFollowers;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    @Override
    public String toString() {
        return "ArtistViewModel{" +
                "artistName='" + artistName + '\'' +
                ", artistFollowers=" + artistFollowers +
                '}';
    }
}
