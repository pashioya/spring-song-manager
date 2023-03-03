package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.SongDto;
import com.kdg.springprojt5.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SongApiController {
    private final SongService songService;

    public SongApiController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<SongDto> getSong(@PathVariable("id") Long songId) {
        var song = songService.getSongById(songId);
        return new ResponseEntity<>(
                new SongDto(song.getSongTitle(), song.getDurationMS(), song.getUrl())
                , HttpStatus.OK);
    }

}
