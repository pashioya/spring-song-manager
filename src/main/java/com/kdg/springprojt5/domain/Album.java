package com.kdg.springprojt5.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Table(name = "albums")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "user_id")
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
    @ToString.Exclude
    private List<Artist> artists;

    @OneToMany(mappedBy = "album")
    @ToString.Exclude
    private List<Song> songs;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Album(String albumName, int officialTrackCount, StatusEnum albumStatus, String genre, LocalDate releaseDate, Long userId) {
        this.albumName = albumName;
        this.officialTrackCount = officialTrackCount;
        this.albumStatus = albumStatus;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.userId = userId;
    }
}
