package com.kdg.springprojt5.controllers.mvc.viewmodel;


public class SongViewModel {
    private Long id;
    private String url;
    private String songTitle;
    private int trackNumber;
    private double durationMS;
    private boolean explicit;
    private String user;


    public SongViewModel() {
    }

    public SongViewModel(Long id, String url, String songTitle, int trackNumber, double durationMS, boolean explicit, String user) {
        this.id = id;
        this.url = url;
        this.songTitle = songTitle;
        this.trackNumber = trackNumber;
        this.durationMS = durationMS;
        this.explicit = explicit;
        this.user = user;
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

    public double getDurationMS() {
        return durationMS;
    }

    public void setDurationMS(double durationMS) {
        this.durationMS = durationMS;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
