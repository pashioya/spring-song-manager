package com.kdg.springprojt5.controllers.api.dto;

public class SongDto {
    private String songTitle;
    private double durationMs;
    private String url;



    public SongDto(String songTitle, double durationMs, String url) {
        this.songTitle = songTitle;
        this.durationMs = durationMs;
        this.url = url;
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
