package com.kdg.springprojt5.controllers.mvc.viewmodel;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class AlbumViewModel {
    @NotBlank(message = "Album Name is mandatory")
    private String albumName;
    private int officialTrackCount;
    private String albumStatus;
    @NotBlank(message = "Genre is mandatory")
    private String genre;
    private LocalDate releaseDate;

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

    @Override
    public String toString() {
        return "AlbumViewModel{" +
                "albumName='" + albumName + '\'' +
                ", officialTrackCount=" + officialTrackCount +
                ", albumStatus='" + albumStatus + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
