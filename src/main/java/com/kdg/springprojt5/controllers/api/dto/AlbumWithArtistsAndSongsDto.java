//package com.kdg.springprojt5.controllers.api.dto;
//
//import com.kdg.springprojt5.domain.Artist;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public class AlbumWithArtistsAndSongsDto {
//    private String albumName;
//    private LocalDate releaseDate;
//    private String genre;
//    private int trackCount;
//    private int officialTrackCount;
//    private String albumStatus;
//    private List<SongDto> songs;
//    private List<Artist> artists;
//
//    public AlbumWithArtistsAndSongsDto() {
//    }
//
//    public AlbumWithArtistsAndSongsDto(String albumName, LocalDate releaseDate, String genre, int trackCount, int officialTrackCount, String albumStatus, List<SongDto> songs, List<Artist> artists) {
//        this.albumName = albumName;
//        this.releaseDate = releaseDate;
//        this.genre = genre;
//        this.trackCount = trackCount;
//        this.officialTrackCount = officialTrackCount;
//        this.albumStatus = albumStatus;
//        this.songs = songs;
//        this.artists = artists;
//    }
//
//    public String getAlbumName() {
//        return albumName;
//    }
//
//    public void setAlbumName(String albumName) {
//        this.albumName = albumName;
//    }
//
//    public List<SongDto> getSongs() {
//        return songs;
//    }
//
//    public void setSongs(List<SongDto> songs) {
//        this.songs = songs;
//    }
//}
