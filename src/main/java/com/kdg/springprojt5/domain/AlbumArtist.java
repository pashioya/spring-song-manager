package com.kdg.springprojt5.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "album_artist")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

    public AlbumArtist(Album album, Artist artist) {
        this.album = album;
        this.artist = artist;
    }
}
