package com.kdg.springprojt5.controllers.mvc.viewmodel;


import com.kdg.springprojt5.domain.Album;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistViewModel {
    private Long id;
    private String artistName;
    private double artistFollowers;
    private String user;
    private List<Album> albums;

}
