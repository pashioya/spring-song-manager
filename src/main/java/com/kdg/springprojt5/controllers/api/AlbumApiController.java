package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.AlbumDto;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//returns information rather than a full page
@RestController
@RequestMapping("/api")
public class AlbumApiController {

    private final AlbumService albumService;
    private final SongService songService;

    public AlbumApiController(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }




    @GetMapping("/album/{id}")
    public ResponseEntity<AlbumDto> getAlbum(
            @PathVariable("id") Long albumId
    ) {
        var album = albumService.getAlbumById(albumId);
        if (album != null) {
            return new ResponseEntity<>(
                    new AlbumDto(
                            album.getAlbumName(),
                            album.getArtists().get(0).getArtistName(),
                            album.getGenre(),
                            album.getReleaseDate().toString()
                    ), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
