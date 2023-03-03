package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.AlbumSongsDto;
import com.kdg.springprojt5.controllers.api.dto.SongDto;
import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AlbumSongApiController {

    private final AlbumService albumService;
    private final SongService songService;

    public AlbumSongApiController(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @GetMapping("/albumsSongs/{id}")
    public ResponseEntity<AlbumSongsDto> getSongsForAlbum(
            @PathVariable("id") Long albumId
    ) {
        var album = albumService.getAlbumById(albumId);

        List<SongDto> songDtos= new ArrayList<>();
        for (Song song: songService.getSongsByAlbumId(albumId))
        {
            SongDto songDto = new SongDto(song.getSongTitle(), song.getDurationMS(), song.getUrl());
            songDtos.add(songDto);
        }

        return new ResponseEntity<>(new AlbumSongsDto(
                album.getAlbumName(),
                songDtos
        ), HttpStatus.OK);
    }

//    function for deleting a song from an album
    @DeleteMapping("/albumsSong/delete/{id}")
    public ResponseEntity<Void> deleteSongFromAlbum(
            @PathVariable long id) {
        songService.deleteSong(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
