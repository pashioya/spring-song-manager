package com.kdg.springprojt5.controllers.api.dto;


import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {

    private Long id;
    private String username;
    private String albumName;
    private String genre;
    private int officialTrackCount;
    private String albumStatus;
    private String releaseDate;
}
