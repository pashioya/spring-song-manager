package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.ArtistDto;
import com.kdg.springprojt5.controllers.api.dto.NewArtistDto;
import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.service.ArtistService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api")
public class ArtistApiController {

    private final Logger logger;
    private final ArtistService artistService;

    private final ModelMapper modelMapper;

    public ArtistApiController(ArtistService artistService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
        this.artistService = artistService;
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<ArtistDto> getArtist(@PathVariable("id") Long artistId) {
        var artist = artistService.getArtistById(artistId);
        return new ResponseEntity<>(
                modelMapper.map(artist, ArtistDto.class), HttpStatus.OK);
    }

//    get all artists
    @GetMapping("/artists")
    public ResponseEntity<Iterable<ArtistDto>> getAllArtists() {
        var artists = artistService.getAllArtists();
        if (artists == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        List<ArtistDto> artistDtos = new ArrayList<>();
        for (Artist artist : artists) {
            ArtistDto artistDto = modelMapper.map(artist, ArtistDto.class);
            artistDto.setId(artist.getId());
            artistDtos.add(artistDto);
        }
        return new ResponseEntity<>(artistDtos, HttpStatus.OK);
    }

//    get all artists for a specific album
    @GetMapping("/album/{albumId}/artists")
    public ResponseEntity<Iterable<ArtistDto>> getAllArtistsForAlbum(@PathVariable("albumId") Long albumId) {
        var artists = artistService.getAllArtistsForAlbum(albumId);
        if (artists == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        List<ArtistDto> artistDtos = new ArrayList<>();
        for (Artist artist : artists) {
            ArtistDto artistDto = modelMapper.map(artist, ArtistDto.class);
            artistDto.setId(artist.getId());
            artistDtos.add(artistDto);
        }
        return new ResponseEntity<>(artistDtos, HttpStatus.OK);
    }

    @DeleteMapping("/artist/{id}/delete")
    public ResponseEntity<Void> deleteArtist(@PathVariable long id) {
        artistService.deleteArtist(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/artist/create")
    public String createArtist(
            @Valid
            @RequestBody NewArtistDto artistDto,
            BindingResult errors
    ) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "addArtist";
        }
        Artist artist = new Artist(
                artistDto.getArtistName(),
                artistDto.getArtistFollowers()
        );
        artistService.saveArtist(artist);
        return "redirect:/allArtists";
    }


    @PostMapping("/album/{albumId}/addArtist")
    public ResponseEntity<RedirectView>  addAlbumToArtist(
            @RequestBody NewArtistDto artistDto,
            BindingResult errors,
            @PathVariable long albumId
    ) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RedirectView("/album/" + albumId));
        }
        Artist artist = new Artist(
                artistDto.getArtistName(),
                artistDto.getArtistFollowers()
        );
        artistService.saveArtist(artist);
        artistService.addArtistToAlbum(artist, albumId);
        return ResponseEntity.ok().body(new RedirectView("/album/" + albumId));
    }
}
