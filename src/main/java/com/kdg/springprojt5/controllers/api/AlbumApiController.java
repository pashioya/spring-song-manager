package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.AlbumDto;
import com.kdg.springprojt5.controllers.mvc.viewmodel.AlbumViewModel;
import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.StatusEnum;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//returns information rather than a full page
@RestController
@RequestMapping("/api")
public class AlbumApiController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final SongService songService;
    @Autowired
    private ModelMapper modelMapper;

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
            AlbumDto albumDto = modelMapper.map(album, AlbumDto.class);
            return new ResponseEntity<>(
                    albumDto, HttpStatus.OK);
        }
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
            return new ResponseEntity<>(albumDtos, HttpStatus.OK);
        }
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
    public ResponseEntity<String> createAlbum(
            @PathVariable("artistId") Long artistId,
            @RequestBody AlbumViewModel viewModel
    ) {

        var album = new Album(
                viewModel.getAlbumName(),
                viewModel.getOfficialTrackCount(),
                StatusEnum.valueOf(viewModel.getAlbumStatus().toUpperCase()),
                viewModel.getGenre(),
                viewModel.getReleaseDate()
        );
        albumService.saveAlbum(album, artistId);
        return new ResponseEntity<>("Album created", HttpStatus.OK);
    }

}
