package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.AlbumDto;
import com.kdg.springprojt5.controllers.api.dto.NewAlbumDto;
import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.StatusEnum;
import com.kdg.springprojt5.security.CustomUserDetails;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/album")
@AllArgsConstructor
public class AlbumApiController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final SongService songService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final ModelMapper modelMapper;

    @GetMapping("/albums")
    public ResponseEntity<List<AlbumDto>> getAllAlbums() {
        var albums = albumService.getAllAlbums();
        if (albums != null) {
            List<AlbumDto> albumDtos = new ArrayList<>();
            for (Album album : albums) {
                AlbumDto albumDto = modelMapper.map(album, AlbumDto.class);
                albumDto.setArtistName(album.getArtists().get(0).getArtistName());
                albumDtos.add(albumDto);
            }
            return new ResponseEntity<>(albumDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}")
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
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/artist/{id}/albums")
    public ResponseEntity<List<AlbumDto>> getAlbumsForArtist(
            @PathVariable("id") Long artistId
    ) {
        var artist = artistService.getArtistById(artistId);
        var albums = artist.getAlbums();
        if (albums != null) {
            List<AlbumDto> albumDtos = new ArrayList<>();
            for (Album album : albums) {
                AlbumDto albumDto = modelMapper.map(album, AlbumDto.class);
                albumDto.setArtistName(artist.getArtistName());
                albumDtos.add(albumDto);
            }
            return new ResponseEntity<>(albumDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbum(
            @PathVariable("id") Long albumId
    ) {
        var album = albumService.getAlbumById(albumId);
        if (album != null) {
            songService.getSongsByAlbumId(albumId).forEach(song -> songService.deleteSong(song.getId()));
            albumService.deleteAlbum(albumId);
            return new ResponseEntity<>("Album deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Album not found", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/artist/{artistId}")
    public String createAlbum(
            @PathVariable("artistId") Long artistId,
            @RequestBody NewAlbumDto albumDto,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        Long userId = currentUser.getUserId();

        var album = new Album(
                albumDto.getAlbumName(),
                albumDto.getOfficialTrackCount(),
                StatusEnum.valueOf(albumDto.getAlbumStatus().toUpperCase()),
                albumDto.getGenre(),
                albumDto.getReleaseDate(),
                userId
        );
        Long id = albumService.saveAlbum(album, artistId).getId();
        return "redirect:/album/" + id;
    }
}
