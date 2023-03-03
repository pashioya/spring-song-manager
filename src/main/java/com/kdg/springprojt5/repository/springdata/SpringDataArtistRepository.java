package com.kdg.springprojt5.repository.springdata;

import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.repository.ArtistRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Profile("springData")
public interface SpringDataArtistRepository extends JpaRepository<Artist, Long>, ArtistRepository {

    @NotNull
    Artist save(@NotNull Artist artist);
    @Query("SELECT a FROM Artist a")
    List<Artist> getAllArtists();
    @Query("select a from Artist a where a.id = ?1")
    Artist getArtistById(long id);

    @Query("SELECT DISTINCT aa.artist FROM AlbumArtist aa WHERE aa.album.id = :albumId")
    List<Artist> getAlbumsArtists(@Param("albumId") long id);

    @Query("update AlbumArtist aa set aa = :albumArtist ")
    void updateAlbumArtist(AlbumArtist albumArtist);

}
