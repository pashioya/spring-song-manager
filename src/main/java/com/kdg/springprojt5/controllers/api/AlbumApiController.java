package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.AlbumDto;
import com.kdg.springprojt5.controllers.api.dto.NewAlbumDto;
import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.StatusEnum;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final Logger logger;

    @Autowired
    private ModelMapper modelMapper;

    public AlbumApiController(AlbumService albumService, ArtistService artistService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
    }




    @GetMapping("/album/{id}")
    public ResponseEntity<AlbumDto> getSingleAlbum(
            @PathVariable("id") Long albumId
    ) {
        var album = albumService.getAlbumById(albumId);
        if (album != null) {
            AlbumDto albumDto = modelMapper.map(album, AlbumDto.class);
            return new ResponseEntity<>(
                    albumDto, HttpStatus.OK);

        }
        logger.info("Album not found");
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/albums")
    public ResponseEntity<Iterable<AlbumDto>> getAllAlbums() {
        var albums = albumService.getAllAlbums();
        if (albums != null) {
            List<AlbumDto> albumDtos = new ArrayList<>();
            for (Album album: albums) {
                AlbumDto albumDto = modelMapper.map(album, AlbumDto.class);
                albumDto.setId(album.getId());
                albumDtos.add(albumDto);
            }
            logger.info("Albums found");
            return new ResponseEntity<>(albumDtos, HttpStatus.OK);
        }
        logger.info("Albums found");
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/artist/{id}/albums")
    public ResponseEntity<Iterable<AlbumDto>> getAlbumsForArtist(
            @PathVariable("id") Long artistId
    ) {
        var artist = artistService.getArtistById(artistId);
        var albums = artist.getAlbums();
        if (albums != null) {
            List<AlbumDto> albumDtos = new ArrayList<>();
            for (Album album: albums) {
                AlbumDto albumDto = modelMapper.map(album, AlbumDto.class);
                albumDto.setArtistName(artist.getArtistName());
                albumDtos.add(albumDto);
            }
            logger.info("Artists Albums found");
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




    @PostMapping("/artist/{artistId}/album/create")
    public String createAlbum(
            @PathVariable("artistId") Long artistId,
            @RequestBody NewAlbumDto albumDto
    ) {

        var album = new Album(
                albumDto.getAlbumName(),
                albumDto.getOfficialTrackCount(),
                StatusEnum.valueOf(albumDto.getAlbumStatus().toUpperCase()),
                albumDto.getGenre(),
                albumDto.getReleaseDate()
        );
        long id = albumService.saveAlbum(album, artistId).getId();
        return "redirect:/album/" + id;
    }

    @PutMapping("/album/{id}/update")
    public String updateAlbum(
            @PathVariable("id") Long albumId,
            @RequestBody NewAlbumDto albumDto
    ) {
        var album = albumService.getAlbumById(albumId);
        if (album != null) {
            album.setAlbumName(albumDto.getAlbumName());
            album.setOfficialTrackCount(albumDto.getOfficialTrackCount());
            album.setAlbumStatus(StatusEnum.valueOf(albumDto.getAlbumStatus().toUpperCase()));
            album.setGenre(albumDto.getGenre());
            album.setReleaseDate(albumDto.getReleaseDate());
            albumService.saveAlbum(album, album.getArtists().get(0).getId());
            return "redirect:/album/" + albumId + "?success=Album updated";
        }
        return "redirect:/album/" + albumId + "?error=Album not found";
    }

}
