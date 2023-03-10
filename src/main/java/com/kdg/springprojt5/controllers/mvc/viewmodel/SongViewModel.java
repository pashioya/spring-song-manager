package com.kdg.springprojt5.controllers.mvc.viewmodel;


public class SongViewModel {
    private String url;
    private String songTitle;
    private String artistName;
    private String albumName;
    private int trackNumber;
    private int durationMS;
    private boolean explicit;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public int getDurationMS() {
        return durationMS;
    }

    public void setDurationMS(int durationMS) {
        this.durationMS = durationMS;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    @Override
    public String toString() {
        return "SongViewModel{" +
                "url='" + url + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", artistName='" + artistName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", trackNumber=" + trackNumber +
                ", durationMS=" + durationMS +
                ", explicit=" + explicit +
                '}';
    }
}
