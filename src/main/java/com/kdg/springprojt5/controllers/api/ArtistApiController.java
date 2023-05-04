package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.ArtistDto;
import com.kdg.springprojt5.controllers.api.dto.NewArtistDto;
import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.security.AdminOnly;
import com.kdg.springprojt5.security.CustomUserDetails;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import jakarta.validation.Valid;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/artist")
@AllArgsConstructor
@Transactional
public class ArtistApiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final ArtistService artistService;
    private final AlbumService albumService;
    private final SongService songService;
    private final ModelMapper modelMapper;


    @GetMapping("/artists")
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        var artists = artistService.getAllArtists();
        if (artists == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        List<ArtistDto> artistDtos = new ArrayList<>();
        for (Artist artist : artists) {
            ArtistDto artistDto = modelMapper.map(artist, ArtistDto.class);
            artistDto.setId(artist.getId());
            artistDtos.add(artistDto);
        }
        return new ResponseEntity<>(artistDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDto> getArtist(@PathVariable("id") Long artistId) {
        try {
            var artist = artistService.getArtistById(artistId);
            ArtistDto artistDto = modelMapper.map(artist, ArtistDto.class);
            artistDto.setUsername(artist.getUser().getUsername());
            return new ResponseEntity<>(artistDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/album/{albumId}/artists")
    public ResponseEntity<List<ArtistDto>> getAllArtistsForAlbum(@PathVariable("albumId") Long albumId) {
        try {
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
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        try {
            Artist artist = artistService.getArtistById(id);
            List<Album> artistAlbums = artist.getAlbums();
            if (artistAlbums == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            for (Album album : artistAlbums) {
                songService.getSongsByAlbumId(album.getId()).forEach(song -> songService.deleteSong(song.getId()));
                albumService.deleteAlbum(album.getId());
            }
            artistService.deleteArtist(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @AdminOnly
    @PostMapping()
    public ResponseEntity<ArtistDto> createArtist(
            @Valid
            @RequestBody NewArtistDto artistDto,
            BindingResult errors,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            Artist artist = modelMapper.map(artistDto, Artist.class);
            if (artist == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            artist.setUserId(currentUser.getUserId());
            artistService.saveArtist(artist);
            ArtistDto artistDto1 = modelMapper.map(artist, ArtistDto.class);
            artistDto1.setUsername(currentUser.getUsername());
            return new ResponseEntity<>(artistDto1, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
