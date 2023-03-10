package com.kdg.springprojt5.controllers.mvc.viewmodel;


public class ArtistViewModel {
    private String artistName;
    private double artistFollowers;
//    private String favoriteGenre;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public double getArtistFollowers() {
        return artistFollowers;
    }

    @Override
    public String toString() {
        return "ArtistViewModel{" +
                "artistName='" + artistName + '\'' +
                ", artistFollowers=" + artistFollowers +
                '}';
    }
}
