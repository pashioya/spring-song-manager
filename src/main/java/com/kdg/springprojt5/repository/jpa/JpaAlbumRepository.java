package com.kdg.springprojt5.repository.jpa;

import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.repository.AlbumRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("jpa")
@Transactional
public class JpaAlbumRepository implements AlbumRepository {

    @PersistenceContext
    private EntityManager em;

    public JpaAlbumRepository() {
    }

    @Override
    public Album save(Album album) {
        em.persist(album);
        return album;
    }

    @Override
    public List<Album> getAllAlbums() {
        return em.createQuery("select a from Album a", Album.class).getResultList();
    }

    @Override
    public Album getAlbumById(Long id) {
        return em.find(Album.class, id);
    }

    @Override
    public List<Album> getAlbumsByArtistId(Long artistId) {
        return em.createQuery("SELECT a FROM Album a JOIN a.artists ar WHERE ar.id = :artistId", Album.class)
                .setParameter("artistId", artistId)
                .getResultList();
    }



    @Override
    public void updateAlbumArtist(AlbumArtist albumArtist) {
        em.persist(albumArtist);
    }

    @Override
    public void deleteAlbumArtist(AlbumArtist albumArtist) {
        em.remove(albumArtist);
    }


    @Override
    public void deleteById(Long id) {
        em.remove(getAlbumById(id));
    }

}
