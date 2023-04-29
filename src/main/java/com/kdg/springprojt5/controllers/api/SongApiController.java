package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.NewSongDto;
import com.kdg.springprojt5.controllers.api.dto.SongDto;
import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.security.CustomUserDetails;
import com.kdg.springprojt5.service.SongService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
@Transactional
@AllArgsConstructor
public class SongApiController {
    private final SongService songService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SongDto> getSong(@PathVariable("id") Long songId) {
        try {
            var song = songService.getSongById(songId);
            SongDto songDto = modelMapper.map(song, SongDto.class);
            songDto.setUsername(song.getUser().getUsername());
            return new ResponseEntity<>(songDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/songs")
    public ResponseEntity<List<SongDto>> getSongs() {

        try {
            var songs = songService.getAllSongs();
            List<SongDto> songDtos = songs.stream()
                    .map(song -> modelMapper.map(song, SongDto.class))
                    .toList();
            return new ResponseEntity<>(songDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/album/{id}/songs")
    public ResponseEntity<List<SongDto>> getAlbumSongs(@PathVariable("id") Long albumId) {
        var songs = songService.getSongsByAlbumId(albumId);
        if (songs != null) {
            List<SongDto> songDtos = songs.stream()
                    .map(song -> modelMapper.map(song, SongDto.class))
                    .toList();
            return new ResponseEntity<>(songDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/album/{albumId}")
    public ResponseEntity<SongDto> addSongToAlbum(
            @PathVariable Long albumId,
            @RequestBody NewSongDto songDto,
            BindingResult errors,
            Authentication authentication
    ) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
        Long userId = currentUser.getUserId();

        Song song = new Song(
                albumId,
                songDto.getUrl(),
                songDto.getSongTitle(),
                songDto.getTrackNumber(),
                songDto.getDurationMS(),
                songDto.isExplicit(),
                userId
        );
        songService.saveSong(song);
        return new ResponseEntity<>(modelMapper.map(song, SongDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
