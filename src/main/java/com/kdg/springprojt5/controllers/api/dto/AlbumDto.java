package com.kdg.springprojt5.controllers.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {

    private Long id;
    private String albumName;
    private String artistName;
    private String genre;
    private int officialTrackCount;
    private String albumStatus;
    private String releaseDate;
}
