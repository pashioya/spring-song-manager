package com.kdg.springprojt5.repository.jpa;

import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.repository.SongRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("jpa")
public class JpaSongRepository implements SongRepository {


    @PersistenceContext
    private EntityManager em;

    public JpaSongRepository() {
    }

    @Override
    @Transactional
    public Song save(Song song) {
        em.persist(song);
        return song;
    }

    @Override
    public List<Song> getAllSongs() {
        return em.createQuery("SELECT s FROM Song s", Song.class).getResultList();
    }

    @Override
    public Song getSongById(long id) {
        return em.find(Song.class, id);
    }

    @Override
    public List<Song> getSongsByAlbumId(long id) {
        return em.createQuery("SELECT s FROM Song s WHERE s.albumId = :id", Song.class).setParameter("id", id).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        em.remove(getSongById(id));
    }

}
