package com.kdg.springprojt5.configuration;

import com.kdg.springprojt5.controllers.api.dto.AlbumDto;
import com.kdg.springprojt5.domain.Album;
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
        modelMapper.addConverter(albumAlbumDtoConverter);
        return modelMapper;
    }
}
