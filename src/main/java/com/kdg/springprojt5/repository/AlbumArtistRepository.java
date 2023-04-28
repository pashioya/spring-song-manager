package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.AlbumArtist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumArtistRepository extends JpaRepository<AlbumArtist, Long> {

    void deleteAlbumArtistByArtistId(Long id);
}
