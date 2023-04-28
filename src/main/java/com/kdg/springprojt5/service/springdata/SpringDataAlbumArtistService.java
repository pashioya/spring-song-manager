package com.kdg.springprojt5.service.springdata;


import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.repository.AlbumArtistRepository;
import com.kdg.springprojt5.service.AlbumArtistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpringDataAlbumArtistService implements AlbumArtistService {

    private final AlbumArtistRepository albumArtistRepository;

    @Override
    public void saveAlbumArtist(AlbumArtist albumArtist) {
        albumArtistRepository.save(albumArtist);
    }

    @Override
    public void deleteAlbumArtist(AlbumArtist albumArtist) {
        albumArtistRepository.delete(albumArtist);
    }

    @Override
    public List<AlbumArtist> getAllAlbumArtists() {
        return albumArtistRepository.findAll();
    }
}
