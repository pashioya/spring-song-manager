package com.kdg.springprojt5.controllers.api.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongDto {
    private Long id;
    private String songTitle;
    private int trackNumber;
    private double durationMs;
    private boolean explicit;
    private String url;
}
