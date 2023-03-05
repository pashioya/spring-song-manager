package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.ArtistDto;
import com.kdg.springprojt5.controllers.mvc.viewmodel.ArtistViewModel;
import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.service.ArtistService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArtistApiController {

    private final ArtistService artistService;

    @Autowired
    private ModelMapper modelMapper;

    public ArtistApiController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<ArtistDto> getArtist(@PathVariable("id") Long artistId) {
        var artist = artistService.getArtistById(artistId);
        return new ResponseEntity<>(
                modelMapper.map(artist, ArtistDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/artist/{id}/delete")
    public ResponseEntity<Void> deleteArtist(@PathVariable long id) {
        artistService.deleteArtist(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/artist/create")
    public ResponseEntity<Void> createArtist(
            @Valid @ModelAttribute ArtistViewModel viewModel, Model model, BindingResult errors, @PathVariable long albumId
    ) {
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
//            return "addSong";
//        }
        Artist artist = new Artist(
                viewModel.getArtistName(),
                viewModel.getArtistFollowers()
        );
        artistService.saveArtist(artist);
        artistService.addArtistToAlbum(artist, albumId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
