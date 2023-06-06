package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumArtistRepository albumArtistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeAll
    public void setUp() {
        userRepository.save(new User(
                "tester",
                passwordEncoder.encode("password"),
                UserRole.ADMIN
        ));
    }

    @AfterAll
    public void tearDown() {
        albumArtistRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testLazyLoading() {
        // Create and save new Artist and Album entities
        Artist artist = new Artist("Test Artist", 156, 1L);
        Album album = new Album("Test Album", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26), 1L);
        artistRepository.save(artist);
        albumRepository.save(album);

        // Create a new AlbumArtist and associate it with the Artist and Album entities
        AlbumArtist albumArtist = new AlbumArtist(album, artist);
        albumArtistRepository.save(albumArtist);

        // Clear the Hibernate session to ensure lazy loading
        entityManager.clear();

        // Retrieve the AlbumArtist by its ID
        AlbumArtist result = albumArtistRepository.findById(albumArtist.getId()).orElseThrow();

        // Verify that the Album and Artist entities have not been loaded yet
        Assertions.assertFalse(Hibernate.isInitialized(result.getAlbum()));
        Assertions.assertFalse(Hibernate.isInitialized(result.getArtist()));

        // Access the Album and Artist entities
        Album albumResult = result.getAlbum();
        System.out.println(albumResult);
        Artist artistResult = result.getArtist();
        System.out.println(artistResult);

        // Verify that the Album and Artist entities have been loaded
        Assertions.assertTrue(Hibernate.isInitialized(result.getAlbum()));
        Assertions.assertTrue(Hibernate.isInitialized(result.getArtist()));

        // Verify the Album and Artist entity data
        Assertions.assertEquals(album.getId(), albumResult.getId());
        Assertions.assertEquals(artist.getId(), artistResult.getId());
    }


    @Test
    public void testAlbumArtistAssociation() {
        // Creating an artist and an album
        Artist artist = new Artist("Test Artist", 156, 1L);
        Album album = new Album("Test Album", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26), 1L);

        // Creating an album artist that associates the artist with the album
        AlbumArtist albumArtist = new AlbumArtist(album, artist);

        // Saving the entities in the database
        artistRepository.save(artist);
        albumRepository.save(album);
        albumArtistRepository.save(albumArtist);

        // Retrieving the album artist and verifying that the association is correct
        AlbumArtist retrievedAlbumArtist = albumArtistRepository.findById(albumArtist.getId()).orElse(null);
        assert retrievedAlbumArtist != null;
        assertEquals(album.getId(), retrievedAlbumArtist.getAlbum().getId());
        assertEquals(artist.getId(), retrievedAlbumArtist.getArtist().getId());

        // Retrieving the album from the album artist and verifying that it's the correct one
        Album retrievedAlbum = retrievedAlbumArtist.getAlbum();
        assert retrievedAlbum != null;
        assertEquals(album.getId(), retrievedAlbum.getId());

        // Retrieving the artist from the album artist and verifying that it's the correct one
        Artist retrievedArtist = retrievedAlbumArtist.getArtist();
        assert retrievedArtist != null;
        assertEquals(artist.getId(), retrievedArtist.getId());
    }


}
