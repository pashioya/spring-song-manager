package com.kdg.springprojt5.configuration;

import com.kdg.springprojt5.controllers.api.dto.AlbumDto;
import com.kdg.springprojt5.controllers.api.dto.ArtistDto;
import com.kdg.springprojt5.controllers.api.dto.SongDto;
import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.domain.Song;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();


        Converter<Album, AlbumDto> albumAlbumDtoConverter = new AbstractConverter<>() {
            @Override
            protected AlbumDto convert(Album source) {
                if (source == null)
                    return null;
                AlbumDto destination = new AlbumDto();
                destination.setId(source.getId());
                destination.setAlbumName(source.getAlbumName());
                destination.setUsername(source.getUser().getUsername());
                destination.setGenre(source.getGenre());
                destination.setAlbumStatus(source.getAlbumStatus().toString());
                destination.setOfficialTrackCount(source.getOfficialTrackCount());
                destination.setReleaseDate(source.getReleaseDate().toString());
                return destination;
            }
        };

        Converter<Artist, ArtistDto> artistArtistDtoConverter = new AbstractConverter<>() {
            @Override
            protected ArtistDto convert(Artist source) {
                if (source == null)
                    return null;

                ArtistDto destination = new ArtistDto();
                destination.setId(source.getId());
                destination.setName(source.getArtistName());
                destination.setUsername(source.getUser().getUsername());
                destination.setArtistFollowers(source.getArtistFollowers());

                return destination;
            }
        };

        Converter<Song, SongDto> songSongDtoConverter = new AbstractConverter<>() {
            @Override
            protected SongDto convert(Song source) {
                if (source == null)
                    return null;

                SongDto destination = new SongDto();
                destination.setId(source.getId());
                destination.setUsername(source.getUser().getUsername());
                destination.setExplicit(source.isExplicit());
                destination.setUrl(source.getUrl());
                destination.setTrackNumber(source.getTrackNumber());
                destination.setSongTitle(source.getSongTitle());

                return destination;
            }
        };
        modelMapper.addConverter(songSongDtoConverter);
        modelMapper.addConverter(artistArtistDtoConverter);
        modelMapper.addConverter(albumAlbumDtoConverter);
        return modelMapper;
    }
}
