package com.kdg.springprojt5.controllers.api.dto;

public class ArtistDto {
    private String name;
    private double artistFollowers;

    public ArtistDto(String name, double artistFollowers) {
        this.name = name;
        this.artistFollowers = artistFollowers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArtistFollowers() {
        return artistFollowers;
    }

    public void setArtistFollowers(double artistFollowers) {
        this.artistFollowers = artistFollowers;
    }
}
