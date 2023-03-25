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

    @Column(name="user_id")
    private Long userId;

    private String url;
    private String songTitle;
    private int trackNumber;
    private double durationMS;
    private boolean explicit;
    @ManyToOne
    @JoinColumn(name="album_id",insertable=false, updatable=false)
    private Album album;

    @ManyToOne
    @JoinColumn(name="user_id",insertable=false, updatable=false)
    private User user;

    public Song() {
    }

    public Song(Long id, Long albumId, String url, String songTitle, int trackNumber, double durationMS, boolean explicit, Long userId) {
        this.id = id;
        this.albumId = albumId;
        this.url = url;
        this.songTitle = songTitle;
        this.trackNumber = trackNumber;
        this.durationMS = durationMS;
        this.explicit = explicit;
        this.userId = userId;
    }

    public Song(Long albumId, String url, String songTitle, int trackNumber, double durationMS, boolean explicit, Long userId) {
        this.albumId = albumId;
        this.url = url;
        this.songTitle = songTitle;
        this.trackNumber = trackNumber;
        this.durationMS = durationMS;
        this.explicit = explicit;
        this.userId = userId;
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


    public int getTrackNumber() {
        return trackNumber;
    }
    public double getDurationMS() {
        return durationMS;
    }
    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public void setDurationMS(double durationMS) {
        this.durationMS = durationMS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return getTrackNumber() == song.getTrackNumber() && Double.compare(song.getDurationMS(), getDurationMS()) == 0 && getAlbumId().equals(song.getAlbumId()) && getUrl().equals(song.getUrl()) && getSongTitle().equals(song.getSongTitle());
    }
}
