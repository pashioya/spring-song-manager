package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.NewSongDto;
import com.kdg.springprojt5.controllers.api.dto.SongDto;
import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.SongService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SongApiController {
    private final SongService songService;
    private final AlbumService albumService;
    private final Logger logger;

    @Autowired
    private ModelMapper modelMapper;

    public SongApiController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
        this.logger = LoggerFactory.getLogger(this.getClass().getName());

    }

    @GetMapping("/song/{id}")
    public ResponseEntity<SongDto> getSong(@PathVariable("id") Long songId) {
        var song = songService.getSongById(songId);
        return new ResponseEntity<>(
                new SongDto(song.getSongTitle(), song.getDurationMS(), song.getUrl())
                , HttpStatus.OK);
    }

    @GetMapping("/songs")
    public ResponseEntity<Iterable<SongDto>> getSongs() {
        var songs = songService.getAllSongs();
        return new ResponseEntity<>(
                modelMapper.map(songs, Iterable.class), HttpStatus.OK);

    }

//    get albums songs
    @GetMapping("/album/{id}/songs")
    public ResponseEntity<Iterable<SongDto>> getAlbumSongs(@PathVariable("id") Long albumId) {
        var songs = albumService.getAlbumById(albumId).getSongs();
        return new ResponseEntity<>(
                songs.stream().map(song -> new SongDto(song.getSongTitle(), song.getDurationMS(), song.getUrl())).toList()
                , HttpStatus.OK);
    }

    @PostMapping("/album/{albumId}/add/song")
    public ResponseEntity<SongDto> addSongToAlbum(@Valid @ModelAttribute NewSongDto songDto, BindingResult errors, HttpSession session, @PathVariable long albumId) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
        }
        Song song = new Song(
                albumId,
                songDto.getUrl(),
                songDto.getSongTitle(),
                songDto.getTrackNumber(),
                songDto.getDurationMS(),
                songDto.isExplicit()
        );
//        logger.debug(viewModel.toString());
        songService.saveSong(song);
        return new ResponseEntity<>(
                new SongDto(
                        song.getSongTitle(),
                        song.getDurationMS(),
                        song.getUrl()
                )
                , HttpStatus.OK);
    }

    @DeleteMapping("/song/{id}/delete")
    public ResponseEntity<Void> deleteSong(@PathVariable long id) {
        songService.deleteSong(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
