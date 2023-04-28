package com.kdg.springprojt5.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof AlbumArtist that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getAlbum(), that.getAlbum()) && Objects.equals(getArtist(), that.getArtist());
    }
}
