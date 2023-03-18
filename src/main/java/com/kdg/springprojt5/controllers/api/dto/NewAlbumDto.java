package com.kdg.springprojt5.controllers.api.dto;

import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.domain.Song;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class NewAlbumDto {
    @NotBlank(message = "Album Name is mandatory")
    private String albumName;
    @Min(value = 1, message = "Track count must be at least 1")
    private int officialTrackCount;
    @NotBlank(message = "Album Name is mandatory")
    private String albumStatus;
    @NotBlank(message = "Genre is mandatory")
    private String genre;
    @NotBlank(message = "Album Name is mandatory")
    private LocalDate releaseDate;
    private List<Artist> artists;
    private List<Song> songs;


    public NewAlbumDto(String albumName, int officialTrackCount, String albumStatus, String genre, LocalDate releaseDate, List<Artist> artists, List<Song> songs) {
        this.albumName = albumName;
        this.officialTrackCount = officialTrackCount;
        this.albumStatus = albumStatus;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.artists = artists;
        this.songs = songs;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getOfficialTrackCount() {
        return officialTrackCount;
    }

    public String getAlbumStatus() {
        return albumStatus;
    }

    public void setAlbumStatus(String albumStatus) {
        this.albumStatus = albumStatus;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setOfficialTrackCount(int officialTrackCount) {
        this.officialTrackCount = officialTrackCount;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
