package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.AlbumDto;
import com.kdg.springprojt5.controllers.api.dto.NewAlbumDto;
import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.domain.StatusEnum;
import com.kdg.springprojt5.security.CustomUserDetails;
import com.kdg.springprojt5.service.AlbumArtistService;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/album")
@Transactional
@AllArgsConstructor
public class AlbumApiController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final AlbumArtistService albumArtistService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final ModelMapper modelMapper;

    @GetMapping("/albums")
    public ResponseEntity<List<AlbumDto>> getAllAlbums() {
        var albums = albumService.getAllAlbums();
        if (albums != null) {
            List<AlbumDto> albumDtos = albums.stream().map(album -> modelMapper.map(album, AlbumDto.class)).toList();
            return ResponseEntity.ok().body(albumDtos);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> getSingleAlbum(
            @PathVariable("id") Long albumId
    ) {
        var album = albumService.getAlbumById(albumId);
        if (album != null) {
            return ResponseEntity.ok().body(modelMapper.map(album, AlbumDto.class));
        }
        logger.info("Album not found");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/artist/{id}/albums")
    public ResponseEntity<List<AlbumDto>> getAlbumsForArtist(@PathVariable("id") Long artistId) {
        try {
            var albums = albumService.getAlbumsByArtistId(artistId);
            if (albums.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok().body(albums.stream().map(album -> modelMapper.map(album, AlbumDto.class)).toList());
        } catch (Exception e) {
            logger.error("Failed to retrieve albums for artist", e);
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbum(
            @PathVariable("id") Long albumId
    ) {
        try {
            var album = albumService.getAlbumById(albumId);
            if (album != null) {
                albumService.deleteAlbum(albumId);
                return new ResponseEntity<>("Album deleted", HttpStatus.OK);
            }
            return new ResponseEntity<>("Album not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/artist/{artistId}")
    public ResponseEntity<AlbumDto> createAlbum(
            @PathVariable("artistId") Long artistId,
            @RequestBody NewAlbumDto albumDto,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        try {
            var album = albumService.saveAlbum(new Album(
                    albumDto.getAlbumName(),
                    albumDto.getOfficialTrackCount(),
                    StatusEnum.valueOf(albumDto.getAlbumStatus()),
                    albumDto.getGenre(),
                    albumDto.getReleaseDate(),
                    currentUser.getUserId()), artistId);
            if (album != null) {
                albumArtistService.saveAlbumArtist(new AlbumArtist(
                        album,
                        artistService.getArtistById(artistId)
                ));
                AlbumDto albumDto1 = modelMapper.map(album, AlbumDto.class);
                albumDto1.setUsername(currentUser.getUsername());
                return new ResponseEntity<>(albumDto1, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
