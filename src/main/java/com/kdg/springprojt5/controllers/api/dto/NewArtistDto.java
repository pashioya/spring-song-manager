package com.kdg.springprojt5.controllers.api.dto;

import jakarta.validation.constraints.NotBlank;

public class NewArtistDto {
    @NotBlank(message = "ArtistName is mandatory")
    private String artistName;
//    @NotBlank(message = "ArtistFollowers is mandatory")
    private double artistFollowers;
//    private String favoriteGenre;

    public NewArtistDto(String artistName, double artistFollowers) {
        this.artistName = artistName;
        this.artistFollowers = artistFollowers;
    }

    public NewArtistDto() {
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
