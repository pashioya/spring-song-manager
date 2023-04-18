package com.kdg.springprojt5.controllers.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewArtistDto {
    @NotBlank(message = "ArtistName is mandatory")
    private String artistName;
    private double artistFollowers;

}
