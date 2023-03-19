package com.kdg.springprojt5.configuration;

import com.kdg.springprojt5.domain.*;
import com.kdg.springprojt5.repository.springdata.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("springData")
public class DataBaseSeeder implements CommandLineRunner {

    private final SpringDataAlbumRepository albumRepository;
    private final SpringDataSongRepository songRepository;
    private final SpringDataArtistRepository artistRepository;

    private final SpringDataAlbumArtistRepository albumArtistRepository;
    private final SpringDataUserRepository userRepository;


    @Autowired
    public DataBaseSeeder(SpringDataAlbumRepository albumRepository, SpringDataSongRepository songRepository, SpringDataArtistRepository artistRepository, SpringDataAlbumArtistRepository albumArtistRepository, SpringDataUserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumArtistRepository = albumArtistRepository;
        this.userRepository = userRepository;
    }

    public void loadArtists() {
        artistRepository.save(new Artist("The Beatles", 1231));
        artistRepository.save(new Artist("The Rolling Stones", 1231));
        artistRepository.save(new Artist("The Doors", 1231));
        artistRepository.save(new Artist("The Who", 1231));
        artistRepository.save(new Artist("The Kinks", 1231));
        artistRepository.save(new Artist("The Animals", 1231));
        artistRepository.save(new Artist("The Yardbirds", 1231));
        artistRepository.save(new Artist("The Jimi Hendrix Experience", 1231));
        artistRepository.save(new Artist("The Velvet Underground", 1231));
    }

    public void loadAlbums() {
        albumRepository.save(new Album("Abbey Road", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26)));
        albumRepository.save(new Album("Let It Bleed", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 8, 5)));
        albumRepository.save(new Album("The Doors", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1967, 1, 4)));
        albumRepository.save(new Album("Who's Next", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1971, 8, 25)));
        albumRepository.save(new Album("Face to Face", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1965, 12, 1)));
        albumRepository.save(new Album("The Animals", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1964, 12, 23)));
        albumRepository.save(new Album("For Your Love", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1965, 12, 1)));
        albumRepository.save(new Album("Are You Experienced", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1967, 9, 19)));
        albumRepository.save(new Album("The Velvet Underground & Nico", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1967, 3, 12)));
    }

    public void loadSongs() {
        songRepository.save(new Song(1, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Tantrum", 2, 134112, true));
        songRepository.save(new Song(1, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "I Don't Wanna Dance", 2, 134112, true));
        songRepository.save(new Song(1, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "You're Not There", 2, 134112, true));
        songRepository.save(new Song(1, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "I'm in Love", 2, 134112, true));
        songRepository.save(new Song(2, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "I'm a Loser", 2, 134112, true));
        songRepository.save(new Song(2, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Baby's in Black", 2, 134112, true));
        songRepository.save(new Song(2, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Rock and Roll Music", 2, 134112, true));
        songRepository.save(new Song(2, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "I'll Follow the Sun", 2, 134112, true));
        songRepository.save(new Song(2, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Mr. Moonlight", 2, 134112, true));
        songRepository.save(new Song(2, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Eight Days a Week", 2, 134112, true));
        songRepository.save(new Song(3, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Words of Love", 2, 134112, true));
        songRepository.save(new Song(4, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Honey Pie", 2, 134112, true));
        songRepository.save(new Song(5, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Every Little Thing", 2, 134112, true));
        songRepository.save(new Song(6, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "I've Just Seen a Face", 2, 134112, true));
        songRepository.save(new Song(7, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Wait", 2, 134112, true));
        songRepository.save(new Song(8, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "If I Needed Someone", 2, 134112, true));
        songRepository.save(new Song(8, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "What You're Doing", 2, 134112, true));
        songRepository.save(new Song(8, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "And Your Bird Can Sing", 2, 134112, true));
        songRepository.save(new Song(9, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Here, There and Everywhere", 2, 134112, true));
    }

    public void loadUsers() {
        userRepository.save(new User("2", "$2a$10$UN/kLIx/smyHJLtOwUzlRuINsnnqtwinBF.xpP.dHRh7epTpuMyC6", UserRole.USER));
        userRepository.save(new User("3", "$2a$10$UN/kLIx/smyHJLtOwUzlRuINsnnqtwinBF.xpP.dHRh7epTpuMyC6", UserRole.ADMIN));
    }

    public void configureAlbumArtist() {
        albumArtistRepository.save(new AlbumArtist(artistRepository.getArtistById(1), albumRepository.getReferenceById(1L)));
        albumArtistRepository.save(new AlbumArtist(artistRepository.getArtistById(2), albumRepository.getReferenceById(2L)));
        albumArtistRepository.save(new AlbumArtist(artistRepository.getArtistById(3), albumRepository.getReferenceById(3L)));
        albumArtistRepository.save(new AlbumArtist(artistRepository.getArtistById(4), albumRepository.getReferenceById(4L)));
        albumArtistRepository.save(new AlbumArtist(artistRepository.getArtistById(5), albumRepository.getReferenceById(5L)));
        albumArtistRepository.save(new AlbumArtist(artistRepository.getArtistById(6), albumRepository.getReferenceById(6L)));
        albumArtistRepository.save(new AlbumArtist(artistRepository.getArtistById(7), albumRepository.getReferenceById(7L)));
        albumArtistRepository.save(new AlbumArtist(artistRepository.getArtistById(8), albumRepository.getReferenceById(8L)));
        albumArtistRepository.save(new AlbumArtist(artistRepository.getArtistById(9), albumRepository.getReferenceById(9L)));
    }


    @Override
    public void run(String... args) throws Exception {
        loadArtists();
        loadAlbums();
        configureAlbumArtist();
        loadSongs();
        loadUsers();
    }
}
