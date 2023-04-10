package com.kdg.springprojt5.controllers.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewSongDto {
    @NotBlank(message = "Url is mandatory")
    private String url;
    @NotBlank(message = "SongTitle is mandatory")
    private String songTitle;
    @NotBlank(message = "trackNumber is mandatory")
    private int trackNumber;
    @NotBlank(message = "duration is mandatory")
    private int durationMS;
    @NotBlank(message = "explicit ? is mandatory")
    private boolean explicit;
}
