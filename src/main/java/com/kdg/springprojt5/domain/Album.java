package com.kdg.springprojt5.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="albums")
public class Album {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name="user_id")
    private Long userId;
    @Column(nullable = false)
    private String albumName;
    @Column(nullable = false)
    private int officialTrackCount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum albumStatus;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private LocalDate releaseDate;


    @ManyToMany
    @JoinTable(name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> artists;

    @OneToMany(mappedBy = "album")
    private List<Song> songs;

    @ManyToOne
    @JoinColumn(name="user_id",insertable=false, updatable=false)
    private User user;

    public Album(Long id, String albumName, int officialTrackCount, StatusEnum albumStatus, String genre, LocalDate releaseDate, Long userId) {
        this.id = id;
        this.albumName = albumName;
        this.officialTrackCount = officialTrackCount;
        this.albumStatus = albumStatus;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.userId = userId;
    }

    public Album() {
    }

    public Album(String albumName, int officialTrackCount, StatusEnum albumStatus, String genre, LocalDate releaseDate, Long userId) {
        this.albumName = albumName;
        this.officialTrackCount = officialTrackCount;
        this.albumStatus = albumStatus;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.userId = userId;
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

    public StatusEnum getAlbumStatus() {
        return albumStatus;
    }

    public void setAlbumStatus(StatusEnum albumStatus) {
        this.albumStatus = albumStatus;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", albumName='" + albumName + '\'' +
                ", officialTrackCount=" + officialTrackCount +
                ", albumStatus=" + albumStatus +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", artists=" + artists +
                ", songs=" + songs +
                '}';
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
