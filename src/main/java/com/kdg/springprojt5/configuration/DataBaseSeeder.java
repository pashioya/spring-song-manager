package com.kdg.springprojt5.configuration;

import com.kdg.springprojt5.domain.*;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import com.kdg.springprojt5.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("springData")
public class DataBaseSeeder implements CommandLineRunner {

    private final ArtistService artistService;
    private final UserService userService;
    private final AlbumService albumService;
    private final SongService songService;


    public DataBaseSeeder(ArtistService artistService, UserService userService, AlbumService albumService, SongService songService) {
        this.artistService = artistService;
        this.userService = userService;
        this.albumService = albumService;
        this.songService = songService;
    }

    public void loadArtists() {
        User admin = userService.getUserById(2L);
        artistService.saveArtist(new Artist("The Beatles", 1231, 2L));
        artistService.saveArtist(new Artist("The Rolling Stones", 1231, 2L));
        artistService.saveArtist(new Artist("The Doors", 1231, 2L));
        artistService.saveArtist(new Artist("The Who", 1231, 2L));
        artistService.saveArtist(new Artist("The Kinks", 1231, 2L));
        artistService.saveArtist(new Artist("The Animals", 1231, 2L));
        artistService.saveArtist(new Artist("The Yardbirds", 1231,  2L));
        artistService.saveArtist(new Artist("The Jimi Hendrix Experience", 1231, 2L));
        artistService.saveArtist(new Artist("The Velvet Underground", 1231, 2L));
    }

    public void loadAlbums() {
        albumService.saveAlbum(new Album("Abbey Road", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 9, 26)), 1L);
        albumService.saveAlbum(new Album("Let It Bleed", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1969, 8, 5)), 2L);
        albumService.saveAlbum(new Album("The Doors", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1967, 1, 4)), 3L);
        albumService.saveAlbum(new Album("Who's Next", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1971, 8, 25)), 4L);
        albumService.saveAlbum(new Album("Face to Face", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1965, 12, 1)), 5L);
        albumService.saveAlbum(new Album("The Animals", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1964, 12, 23)), 6L);
        albumService.saveAlbum(new Album("For Your Love", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1965, 12, 1)), 7L);
        albumService.saveAlbum(new Album("Are You Experienced", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1967, 9, 19)), 8L);
        albumService.saveAlbum(new Album("The Velvet Underground & Nico", 12, StatusEnum.SINGLE, "Rock", LocalDate.of(1967, 3, 12)), 9L);
    }

    public void loadSongs() {
        songService.saveSong(new Song(1L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Tantrum", 2, 134112, true));
        songService.saveSong(new Song(1L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "I Don't Wanna Dance", 2, 134112, true));
        songService.saveSong(new Song(1L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "You're Not There", 2, 134112, true));
        songService.saveSong(new Song(1L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "I'm in Love", 2, 134112, true));
        songService.saveSong(new Song(2L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "I'm a Loser", 2, 134112, true));
        songService.saveSong(new Song(2L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Baby's in Black", 2, 134112, true));
        songService.saveSong(new Song(2L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Rock and Roll Music", 2, 134112, true));
        songService.saveSong(new Song(2L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "I'll Follow the Sun", 2, 134112, true));
        songService.saveSong(new Song(2L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Mr. Moonlight", 2, 134112, true));
        songService.saveSong(new Song(2L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Eight Days a Week", 2, 134112, true));
        songService.saveSong(new Song(3L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Words of Love", 2, 134112, true));
        songService.saveSong(new Song(4L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Honey Pie", 2, 134112, true));
        songService.saveSong(new Song(5L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Every Little Thing", 2, 134112, true));
        songService.saveSong(new Song(6L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "I've Just Seen a Face", 2, 134112, true));
        songService.saveSong(new Song(7L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Wait", 2, 134112, true));
        songService.saveSong(new Song(8L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "If I Needed Someone", 2, 134112, true));
        songService.saveSong(new Song(8L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "What You're Doing", 2, 134112, true));
        songService.saveSong(new Song(8L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "And Your Bird Can Sing", 2, 134112, true));
        songService.saveSong(new Song(9L, "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO", "Here, There and Everywhere", 2, 134112, true));
    }

    public void loadUsers() {
        userService.saveUser(new User("john_smith","user",UserRole.USER));
        userService.saveUser(new User("admin","admin",UserRole.ADMIN));
    }


    @Override
    public void run(String... args) throws Exception {
        loadUsers();
        loadArtists();
        loadAlbums();
        loadSongs();
    }
}
