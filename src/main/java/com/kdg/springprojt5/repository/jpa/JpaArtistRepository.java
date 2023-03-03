package com.kdg.springprojt5.repository.jpa;

import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.repository.ArtistRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("jpa")
@Transactional
public class JpaArtistRepository implements ArtistRepository {

    @PersistenceContext
    private EntityManager em;

    public JpaArtistRepository() {
    }

    @Override
    public Artist save(Artist artist) {
        em.persist(artist);
        return artist;
    }

    @Override
    public List<Artist> getAllArtists() {

        return em.createQuery("select a from Artist a ", Artist.class)
                .getResultList();
    }

    @Override
    public Artist getArtistById(long id) {
        return em.find(Artist.class, id);
    }

    @Override
    public List<Artist> getAlbumsArtists(long id) {
        TypedQuery<Artist> query = em.createQuery("SELECT aa.artist FROM AlbumArtist aa WHERE aa.album.id = :id", Artist.class);
        query.setParameter("id", id);
        return query.getResultList();
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
    @Transactional
    public void deleteById(long id) {
        em.remove(getArtistById(id));
    }


}
