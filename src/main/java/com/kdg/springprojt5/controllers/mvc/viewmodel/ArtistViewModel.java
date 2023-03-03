package com.kdg.springprojt5.controllers.mvc.viewmodel;


import jakarta.validation.constraints.NotBlank;

public class ArtistViewModel {

    @NotBlank(message = "ArtistName is mandatory")
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

    public void setArtistFollowers(double artistFollowers) {
        this.artistFollowers = artistFollowers;
    }

    @Override
    public String toString() {
        return "ArtistViewModel{" +
                "artistName='" + artistName + '\'' +
                ", artistFollowers=" + artistFollowers +
                '}';
    }
}
