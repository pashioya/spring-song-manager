package com.kdg.springprojt5.controllers.api.dto;

public class ArtistDto {
    private Long id;
    private String name;
    private double artistFollowers;

    public ArtistDto(String name, double artistFollowers) {
        this.name = name;
        this.artistFollowers = artistFollowers;
    }

    public ArtistDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
