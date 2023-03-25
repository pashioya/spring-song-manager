package com.kdg.springprojt5.controllers.mvc.viewmodel;

import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.domain.Song;

import java.time.LocalDate;
import java.util.List;

public class AlbumViewModel {

    private Long id;
    private String albumName;
    private int officialTrackCount;
    private String albumStatus;
    private String genre;
    private LocalDate releaseDate;
    private String user;
    private List<Artist> artists;
    private List<Song> songs;


    public AlbumViewModel() {
    }

    public AlbumViewModel(Long id, String albumName, int officialTrackCount, String albumStatus, String genre, LocalDate releaseDate, List<Artist> artists, List<Song> songs, String user) {
        this.id = id;
        this.albumName = albumName;
        this.officialTrackCount = officialTrackCount;
        this.albumStatus = albumStatus;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.artists = artists;
        this.songs = songs;
        this.user = user;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
