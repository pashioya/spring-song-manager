package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpringDataArtistRepository extends JpaRepository<Artist, Long> {
    @Query("SELECT a FROM Artist a")
    List<Artist> getAllArtists();

    @Query("select a from Artist a where a.id = ?1")
    Artist getArtistById(Long id);

    @Query("SELECT DISTINCT aa.artist FROM AlbumArtist aa WHERE aa.album.id = :albumId")
    List<Artist> getAlbumsArtists(@Param("albumId") Long id);

}
