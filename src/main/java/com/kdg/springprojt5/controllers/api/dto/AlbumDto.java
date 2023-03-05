package com.kdg.springprojt5.controllers.api.dto;

public class AlbumDto {

    private long id;
    private String albumName;
    private String artistName;
    private String genre;
    private int officialTrackCount;
    private String albumStatus;
    private String releaseDate;

    public AlbumDto() {
    }

    public AlbumDto(long id, String albumName, String artistName, String genre, int officialTrackCount, String albumStatus, String releaseDate) {
        this.id = id;
        this.albumName = albumName;
        this.artistName = artistName;
        this.genre = genre;
        this.officialTrackCount = officialTrackCount;
        this.albumStatus = albumStatus;
        this.releaseDate = releaseDate;
    }

    public int getOfficialTrackCount() {
        return officialTrackCount;
    }

    public void setOfficialTrackCount(int officialTrackCount) {
        this.officialTrackCount = officialTrackCount;
    }

    public String getAlbumStatus() {
        return albumStatus;
    }

    public void setAlbumStatus(String albumStatus) {
        this.albumStatus = albumStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
