package spring.proj.t3.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import spring.proj.t3.domain.Album;
import spring.proj.t3.domain.Artist;
import spring.proj.t3.domain.Song;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataFactory {
    public static List<Song> allSongs = new ArrayList<Song>();
    public static List<Album> allAlbums = new ArrayList<Album>();
    public static List<Artist> allArtists = new ArrayList<Artist>();
    public static List<Album> ArtistsSongs;

    public static void seed() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        File file = new File("S:\\Development\\kdg\\Programming\\spring-proj-t3\\app\\src\\main\\resources\\json\\TrialSongs.json");
        try {
            BufferedReader data = new BufferedReader(new FileReader(file));
            Type listType = new TypeToken<List<Song>>() {
            }.getType();
            List<Song> Songs = gson.fromJson(data, listType);
            allSongs.addAll(Songs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllArtistsNames() {
        List<String> artists = new ArrayList<String>();
        for (Song song : allSongs) {
            if (!artists.contains(song.getArtistName())) {
                artists.add(song.getArtistName());
            }
        }
        return artists;
    }

    public static List<String> getAllAlbumNames() {
        List<String> albums = new ArrayList<String>();
        for (Song song : allSongs) {
            if (!albums.contains(song.getAlbumName())) {
                albums.add(song.getAlbumName());
            }
        }
        return albums;
    }

    public static void createAllAlbums() {
        List<String> albumNames = getAllAlbumNames();
//            loop through all songs and create a list of songs for each album
        for (String albumName : albumNames) {
            List<Song> albumsSongs = new ArrayList<Song>();
            for (Song song : allSongs) {
                if (song.getAlbumName().equals(albumName)) {
                    albumsSongs.add(song);
                }
            }
//                create an album object for each album
            allAlbums.add(new Album(albumName, albumsSongs));
        }
//            set featured artists for each album
        for (Album album : allAlbums) {
            album.setFeaturedArtists(album.findFeaturedArtists());
        }
    }

    public static void createAllArtists() {
        List<String> artistNames = getAllArtistsNames();
        List<String> MainArtists = new ArrayList<String>();
        for (String artistName : artistNames) {
            artistName = artistName.split(" -")[0];
            if (!MainArtists.contains(artistName)) {
                MainArtists.add(artistName);
            }
        }
//            loop through all songs and create a list of songs for each artist
        for (String artistName : MainArtists) {
            List<Album> artistsAlbums = new ArrayList<Album>();
            for (Album album : allAlbums) {
                if (album.getAlbumsArtistName().equals(artistName)) {
                    artistsAlbums.add(album);
                }
            }
//                    create an artist object for each artist name
            allArtists.add(new Artist(artistName, artistsAlbums));
        }
    }

    //    getters
    public static List<Song> getAllSongs() {
        return allSongs;
    }

    public static List<Album> getArtistsSongs() {
        return ArtistsSongs;
    }

    public static List<Album> getAllAlbums() {
        return allAlbums;
    }

    public static List<Artist> getAllArtists() {
        return allArtists;
    }

    public static List<Song> getSongsOfGenre(String nextLine) {
        List<Song> songsOfGenre = new ArrayList<Song>();
        for (Song song : allSongs) {
            if (song.getGenre().contains(nextLine)) {
                songsOfGenre.add(song);
            }
        }
        return songsOfGenre;
    }

    public static List<Album> getAlbumsOfArtist(String artistName) {
        //            loop through all artists and find the artist with the given name
        List<Album> albumsOfArtist = new ArrayList<Album>();
        for (Artist artist : allArtists) {
            if (artist.getArtistName().contains(artistName)) {
                albumsOfArtist.addAll(artist.getAlbums());
            }
        }
        return albumsOfArtist;
    }

    public static List<Album> getAlbumsOfArtistExplicit(String artistName, boolean explicit) {
        List<Album> albumsOfArtist = getAlbumsOfArtist(artistName);
//        loop through all albums of the artist and find the albums with the given explicit value
        List<Album> albumsOfArtistExplicit = new ArrayList<Album>();
        for (Album album : albumsOfArtist) {
            if (album.isExplicit() == explicit) {
                albumsOfArtistExplicit.add(album);
            }
        }
        return albumsOfArtistExplicit;
    }

    public static List<Album> getExplicitAlbums(boolean explicit) {
        List<Album> explicitAlbums = new ArrayList<Album>();
        for (Album album : allAlbums) {
            if (album.isExplicit()) {
                explicitAlbums.add(album);
            }
        }
        return explicitAlbums;
    }
}
