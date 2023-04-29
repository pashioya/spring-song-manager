package com.kdg.springprojt5.controllers.api.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {
    private Long id;
    private String name;
    private double artistFollowers;
    private String username;
}
