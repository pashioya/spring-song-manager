package com.kdg.springprojt5.controllers.mvc.viewmodel;


import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongViewModel {
    private Long id;
    private String url;
    private String songTitle;
    private int trackNumber;
    private double durationMS;
    private boolean explicit;
    private String user;
}
