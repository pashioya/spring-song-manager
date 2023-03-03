package com.kdg.springprojt5.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="artists")
public class Artist {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="artist_name")
    private String artistName;
    private double artistFollowers;
    private String favoriteGenre;

    @ManyToMany(mappedBy = "artists")
    private List<Album> albums = new ArrayList<>();

    // constructor, getters and setters


    public Artist(Long id, String artist_name, double artist_followers) {
        this.id = id;
        this.artistName = artist_name;
        this.artistFollowers = artist_followers;
        this.favoriteGenre = "";
    }

    public Artist(String artist_name, double artist_followers) {
        this.artistName = artist_name;
        this.artistFollowers = artist_followers;
        this.favoriteGenre = "";
    }

    public Artist() {}

    public void calcFavoriteGenre() {
        Map<String, Integer> genreCounts = new HashMap<>();

        for (Album album : getAlbums()) {
            String genre = album.getGenre();
            int count = genreCounts.getOrDefault(genre, 0);
            genreCounts.put(genre, count + 1);
        }

        String mostPopularGenre = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : genreCounts.entrySet()) {
            String genre = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                mostPopularGenre = genre;
                maxCount = count;
            }
        }
        if (mostPopularGenre.equals("")) {
            mostPopularGenre = "No genre";
        }
        this.favoriteGenre = mostPopularGenre;
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

    public void setArtistFollowers(double artistFollowers) {
        this.artistFollowers = artistFollowers;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
