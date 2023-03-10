package com.kdg.springprojt5.controllers.api.dto;

public class SongDto {
    private long id;
    private String songTitle;
    private double durationMs;
    private String url;



    public SongDto(String songTitle, double durationMs, String url) {
        this.songTitle = songTitle;
        this.durationMs = durationMs;
        this.url = url;
    }

    public SongDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
