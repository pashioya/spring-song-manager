package com.kdg.springprojt5.service.springdata;


import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.repository.SpringDataAlbumArtistRepository;
import com.kdg.springprojt5.service.AlbumArtistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpringDataAlbumArtistService implements AlbumArtistService {

    private final SpringDataAlbumArtistRepository albumArtistRepository;

    @Override
    public void saveAlbumArtist(AlbumArtist albumArtist) {
        albumArtistRepository.save(albumArtist);
    }
}
