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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            if (song == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            var songDto = modelMapper.map(song, SongDto.class);
            return new ResponseEntity<>(songDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    //    find song by title
    @GetMapping("/title/{title}")
    public ResponseEntity<List<SongDto>> getSongByTitle(@PathVariable("title") String title) {
        try {
            var songs = songService.getSongsByTitle(title);
            if (songs == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            List<SongDto> songDtos = songs.stream()
                    .map(song -> modelMapper.map(song, SongDto.class))
                    .toList();
            return new ResponseEntity<>(songDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/songs")
    public ResponseEntity<List<SongDto>> getSongs() {
        try {
            var songs = songService.getAllSongs();
            if (songs == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            List<SongDto> songDtos = songs.stream()
                    .map(song -> modelMapper.map(song, SongDto.class))
                    .toList();
            return new ResponseEntity<>(songDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/album/{id}/songs")
    public ResponseEntity<List<SongDto>> getAlbumSongs(@PathVariable("id") Long albumId) {
        try {
            var songs = songService.getSongsByAlbumId(albumId);
            if (songs == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            List<SongDto> songDtos = songs.stream()
                    .map(song -> modelMapper.map(song, SongDto.class))
                    .toList();
            return new ResponseEntity<>(songDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/album/{albumId}")
    public ResponseEntity<SongDto> addSongToAlbum(
            @PathVariable Long albumId,
            @RequestBody NewSongDto songDto,
            BindingResult errors,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        System.out.println(currentUser);
        try {
            var song = songService.saveSong(
                    new Song(albumId,
                            songDto.getUrl(),
                            songDto.getSongTitle(),
                            songDto.getTrackNumber(),
                            songDto.getDurationMS(),
                            songDto.isExplicit(),
                            currentUser.getUserId())
            );
            SongDto songDto1 = modelMapper.map(song, SongDto.class);
            songDto1.setUsername(currentUser.getUsername());
            return new ResponseEntity<>(songDto1, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        try {
            songService.deleteSong(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
