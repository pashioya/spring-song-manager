//package com.kdg.springprojt5.controllers.api;
//
//import com.kdg.springprojt5.controllers.api.dto.AlbumWithArtistsAndSongsDto;
//import com.kdg.springprojt5.controllers.api.dto.SongDto;
//import com.kdg.springprojt5.service.AlbumService;
//import com.kdg.springprojt5.service.SongService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class AlbumSongApiController {
//
//    private final AlbumService albumService;
//    private final SongService songService;
//
//    public AlbumSongApiController(AlbumService albumService, SongService songService) {
//        this.albumService = albumService;
//        this.songService = songService;
//    }
//
//    @GetMapping("/albumsSongs/{id}")
//    public ResponseEntity<AlbumWithArtistsAndSongsDto> getSongsForAlbum(
//            @PathVariable("id") Long albumId
//    ) {
//        var songs = songService.getSongsByAlbumId(albumId);
//        var album = albumService.getAlbumById(albumId);
//        var artists = album.getArtists();
//
//        List<SongDto> songsDtos= songs.stream().map(song -> new SongDto(
//                song.getSongTitle(),
//                song.getDurationMS(),
//                song.getUrl()
//        )).toList();
//
//
//        return new ResponseEntity<>(new AlbumWithArtistsAndSongsDto(
//                album.getAlbumName(),
//                album.getReleaseDate(),
//                album.getGenre(),
//                album.getOfficialTrackCount(),
//                album.getOfficialTrackCount(),
//                album.getAlbumStatus().toString(),
//                songsDtos,
//                album.getArtists()
//        ), HttpStatus.OK);
//
//    }
//
//}
