package com.kdg.springprojt5.controllers.api.dto;

import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.domain.Song;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewAlbumDto {
    @NotBlank(message = "Album Name is mandatory")
    private String albumName;
    @Min(value = 1, message = "Track count must be at least 1")
    private int officialTrackCount;
    @NotBlank(message = "Album Name is mandatory")
    private String albumStatus;
    @NotBlank(message = "Genre is mandatory")
    private String genre;
    @NotBlank(message = "Album Name is mandatory")
    private LocalDate releaseDate;
    private List<Artist> artists;
    private List<Song> songs;
}
