package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.ArtistDto;
import com.kdg.springprojt5.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArtistApiController {

    private final ArtistService artistService;

    public ArtistApiController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<ArtistDto> getArtist(@PathVariable("id") Long artistId) {
        var artist = artistService.getArtistById(artistId);
        return new ResponseEntity<>(
                new ArtistDto(artist.getArtistName(), artist.getArtistFollowers())
                , HttpStatus.OK);
    }

    @DeleteMapping("/artist/{id}/delete")
    public ResponseEntity<Void> deleteArtist(@PathVariable long id) {
        artistService.deleteArtist(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
