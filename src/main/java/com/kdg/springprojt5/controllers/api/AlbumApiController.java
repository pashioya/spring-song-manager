package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.AlbumDto;
import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//returns information rather than a full page
@RestController
@RequestMapping("/api")
public class AlbumApiController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final SongService songService;

    public AlbumApiController(AlbumService albumService, ArtistService artistService, SongService songService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.songService = songService;
    }




    @GetMapping("/album/{id}")
    public ResponseEntity<AlbumDto> getSingleAlbum(
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

//    function for getting all albums for an artist
    @GetMapping("/artist/{id}/albums")
    public ResponseEntity<Iterable<AlbumDto>> getAlbumsForArtist(
            @PathVariable("id") Long artistId
    ) {
        var artist = artistService.getArtistById(artistId);
        var albums = artist.getAlbums();
        if (albums != null) {
            List<AlbumDto> albumDtos = new ArrayList<>();
            for (Album album: albums) {
                albumDtos.add(new AlbumDto(
                        album.getAlbumName(),
                        artist.getArtistName(),
                        album.getGenre(),
                        album.getReleaseDate().toString()
                ));
            }
            return new ResponseEntity<>(albumDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/album/{id}/delete")
    public ResponseEntity<String> deleteAlbum(
            @PathVariable("id") Long albumId
    ) {
        var album = albumService.getAlbumById(albumId);
        if (album != null) {
            albumService.deleteAlbum(albumId);
            return new ResponseEntity<>("Album deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Album not found", HttpStatus.NOT_FOUND);
    }

}
