package com.kdg.springprojt5.repository.springdata;

import com.kdg.springprojt5.domain.Album;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("springData")
public interface SpringDataAlbumRepository extends JpaRepository<Album, Long> {



//    @NotNull
//    Album save(@NotNull Album album);
//    @Query("SELECT a FROM Album a")
//    List<Album> getAllAlbums();
    @Query("SELECT a FROM Album a JOIN a.artists ar WHERE ar.id = :id")
    List<Album> getAlbumsByArtistId(@Param("id") long id);

//    @Query("update AlbumArtist aa set aa = :albumArtist ")
//    void updateAlbumArtist(AlbumArtist albumArtist);
//
//    @Query("delete from AlbumArtist aa where aa = :albumArtist")
//    void deleteAlbumArtist(AlbumArtist albumArtist);
}
