package com.kdg.springprojt5.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "album_artist")
public class AlbumArtist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public AlbumArtist() {
    }

    public AlbumArtist(Artist artist, Album album) {
        this.album = album;
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    public Album getAlbum() {
        return album;
    }
}
