package com.kdg.springprojt5.domain;

import jakarta.persistence.*;

@Entity
@Table(name="songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="album_id")
    private Long albumId;

    private String url;
    private String songTitle;
    private int trackNumber;
    private double durationMS;
    private boolean explicit;
    @ManyToOne
    @JoinColumn(name="album_id",insertable=false, updatable=false)
    private Album album;

    public Song() {
    }

    public Song(Long id, Long albumId, String url, String songTitle, int trackNumber, double durationMS, boolean explicit) {
        this.id = id;
        this.albumId = albumId;
        this.url = url;
        this.songTitle = songTitle;
        this.trackNumber = trackNumber;
        this.durationMS = durationMS;
        this.explicit = explicit;
    }

    public Song(Long albumId, String url, String songTitle, int trackNumber, double durationMS, boolean explicit) {
        this.albumId = albumId;
        this.url = url;
        this.songTitle = songTitle;
        this.trackNumber = trackNumber;
        this.durationMS = durationMS;
        this.explicit = explicit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
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

    public double getDurationMS() {
        return durationMS;
    }

    public void setDurationMS(double durationMS) {
        this.durationMS = durationMS;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }
}
