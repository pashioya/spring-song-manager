package com.kdg.springprojt5.domain;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "songs")
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "album_id")
    private Long albumId;

    @Column(name = "user_id")
    private Long userId;

    private String url;
    private String songTitle;
    private int trackNumber;
    private double durationMS;
    private boolean explicit;
    @ManyToOne
    @JoinColumn(name = "album_id", insertable = false, updatable = false)
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Song(Long albumId, String url, String songTitle, int trackNumber, double durationMS, boolean explicit, Long userId) {
        this.albumId = albumId;
        this.url = url;
        this.songTitle = songTitle;
        this.trackNumber = trackNumber;
        this.durationMS = durationMS;
        this.explicit = explicit;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return getTrackNumber() == song.getTrackNumber() && Double.compare(song.getDurationMS(), getDurationMS()) == 0 && getAlbumId().equals(song.getAlbumId()) && getUrl().equals(song.getUrl()) && getSongTitle().equals(song.getSongTitle());
    }
}
