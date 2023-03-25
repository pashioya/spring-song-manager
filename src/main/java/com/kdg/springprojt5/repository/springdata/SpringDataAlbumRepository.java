package com.kdg.springprojt5.repository.springdata;

import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.AlbumArtist;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("springData")
public interface SpringDataAlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT a FROM Album a JOIN a.artists ar WHERE ar.id = :id")
    List<Album> getAlbumsByArtistId(@Param("id") Long id);

    @Query("update AlbumArtist aa set aa = :albumArtist ")
    void updateAlbumArtist(AlbumArtist albumArtist);
//
}
