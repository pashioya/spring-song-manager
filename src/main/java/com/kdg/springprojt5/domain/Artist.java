package com.kdg.springprojt5.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "artist_name")
    private String artistName;
    private double artistFollowers;

    @ManyToMany(mappedBy = "artists")
    @ToString.Exclude
    private List<Album> albums = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Artist(String artist_name, double artist_followers, Long userId) {
        this.artistName = artist_name;
        this.artistFollowers = artist_followers;
        this.userId = userId;
    }

}
