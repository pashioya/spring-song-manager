package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.AlbumSongsDto;
import com.kdg.springprojt5.controllers.api.dto.SongDto;
import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/albumSongs/{id}")
    public ResponseEntity<AlbumSongsDto> getAlbumWithSongs(
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
}
