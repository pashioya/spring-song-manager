package com.kdg.springprojt5.controllers.mvc.viewmodel;

import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.domain.Song;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumViewModel {
    private Long id;
    private String albumName;
    private int officialTrackCount;
    private String albumStatus;
    private String genre;
    private LocalDate releaseDate;
    private String user;
    private List<Artist> artists;
    private List<Song> songs;
}
