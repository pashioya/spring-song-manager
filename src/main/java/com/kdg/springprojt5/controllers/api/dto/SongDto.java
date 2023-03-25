package com.kdg.springprojt5.controllers.api.dto;

public class SongDto {
    private Long id;
    private String songTitle;
    private int trackNumber;
    private double durationMs;
    private boolean explicit;
    private String url;


    public SongDto(Long id, String songTitle, int trackNumber, double durationMs, boolean explicit, String url) {
        this.id = id;
        this.songTitle = songTitle;
        this.trackNumber = trackNumber;
        this.durationMs = durationMs;
        this.explicit = explicit;
        this.url = url;
    }

    public SongDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public double getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(double durationMs) {
        this.durationMs = durationMs;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
