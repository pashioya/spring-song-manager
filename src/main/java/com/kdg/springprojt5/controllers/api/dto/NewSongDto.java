package com.kdg.springprojt5.controllers.api.dto;

import jakarta.validation.constraints.NotBlank;

public class NewSongDto {
    @NotBlank(message = "Url is mandatory")
    private String url;
    @NotBlank(message = "SongTitle is mandatory")
    private String songTitle;
    @NotBlank(message = "trackNumber is mandatory")
    private int trackNumber;
    @NotBlank(message = "duration is mandatory")
    private int durationMS;
    @NotBlank(message = "explicit ? is mandatory")
    private boolean explicit;

    public NewSongDto(String url, String songTitle, int trackNumber, int durationMS, boolean explicit) {
        this.url = url;
        this.songTitle = songTitle;
        this.trackNumber = trackNumber;
        this.durationMS = durationMS;
        this.explicit = explicit;
    }

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
                ", trackNumber=" + trackNumber +
                ", durationMS=" + durationMS +
                ", explicit=" + explicit +
                '}';
    }
}
