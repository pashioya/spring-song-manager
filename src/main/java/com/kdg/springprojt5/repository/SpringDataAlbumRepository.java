package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataAlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT a FROM Album a JOIN a.artists ar WHERE ar.id = :id")
    List<Album> getAlbumsByArtistId(@Param("id") Long id);
}
