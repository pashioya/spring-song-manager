package com.kdg.springprojt5.repository.springdata;

import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.domain.Artist;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Profile("springData")
public interface SpringDataArtistRepository extends JpaRepository<Artist, Long> {
    @Query("SELECT a FROM Artist a")
    List<Artist> getAllArtists();
    @Query("select a from Artist a where a.id = ?1")
    Artist getArtistById(Long id);

    @Query("SELECT DISTINCT aa.artist FROM AlbumArtist aa WHERE aa.album.id = :albumId")
    List<Artist> getAlbumsArtists(@Param("albumId") Long id);

    @Query("update AlbumArtist aa set aa = :albumArtist ")
    void updateAlbumArtist(AlbumArtist albumArtist);

}
