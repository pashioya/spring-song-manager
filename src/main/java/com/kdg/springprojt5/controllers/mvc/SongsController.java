package com.kdg.springprojt5.controllers.mvc;

import com.kdg.springprojt5.controllers.mvc.helper.DataItem;
import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import com.kdg.springprojt5.controllers.mvc.viewmodel.SongViewModel;
import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/allSongs")
public class SongsController {
    private final Logger logger;
    private final SongService songService;
    private final ArtistService artistService;

    private final AlbumService albumService;

    public SongsController(SongService songService, ArtistService artistService, AlbumService albumService) {
        this.artistService = artistService;
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.songService = songService;
        this.albumService = albumService;
    }
    @GetMapping
    public String songs(Model model, HttpSession session) {
        setHistory(session, "All Songs");
        model.addAttribute("title", "Songs");
        model.addAttribute("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("homeButton"),
                new DataItem("allSongs", "active"),
                new DataItem("allAlbums"),
                new DataItem("allArtists")

        )));
        model.addAttribute("footerList", new ArrayList<>(Arrays.asList(
                new DataItem("gitLab"),
                new DataItem("pageHistory")

        )));

        if(session.getAttribute("sPageNumbers") == null){
            session.setAttribute("sPageNumbers", 1);
        }
        int sPageNumbers = (int) session.getAttribute("sPageNumbers");
        model.addAttribute("songs", getSongs(sPageNumbers, 20));
        model.addAttribute("totalPages", artistService.getAllArtists().size() / 20 + 1);
        model.addAttribute("sPageNumbers", sPageNumbers);


        return "allSongs";
    }

    public List<Song> getSongs(int page, int pageSize) {
        List<Song> artists = songService.getAllSongs();
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, artists.size());
        return artists.subList(start, end);
    }
    @GetMapping("/nextPage")
    public String nextPage(HttpSession session){
        int sPageNumbers = (int) session.getAttribute("sPageNumbers");
        session.setAttribute("sPageNumbers", sPageNumbers + 1);
        return "redirect:/allSongs";
    }

    @GetMapping("/previousPage")
    public String previousPage(HttpSession session){
        int sPageNumbers = (int) session.getAttribute("sPageNumbers");
        if(sPageNumbers > 1){
            session.setAttribute("sPageNumbers", sPageNumbers - 1);
        }
        return "redirect:/allSongs";
    }

    @GetMapping("/firstPage")
    public String firstPage(HttpSession session){
        session.setAttribute("sPageNumbers", 1);
        return "redirect:/allSongs";
    }

    @GetMapping("/lastPage")
    public String lastPage(HttpSession session){
        session.setAttribute("sPageNumbers", artistService.getAllArtists().size() / 20 + 1);
        return "redirect:/allSongs";
    }

    @GetMapping("/fullSong/{id}")
    public String fullSong(Model model, @PathVariable long id, HttpSession session) {
        setHistory(session, "Full Song: " + id);
        try {
            model.addAttribute("song", songService.getSongById(id));
            model.addAttribute("artists", albumService.getAlbumById(songService.getSongById(id).getAlbumId()).getArtists());
        }
        catch (Exception e){
            throw new EntityNotFoundException("Song with id " + id + " not found");
        }

        model.addAttribute("title", "Songs");
        model.addAttribute("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("homeButton"),
                new DataItem("allSongs", "active"),
                new DataItem("allAlbums"),
                new DataItem("allArtists")

        )));
        model.addAttribute("footerList", new ArrayList<>(Arrays.asList(
                new DataItem("deleteSong"),
                new DataItem("printSong")
        )));

        return "fullSong";
    }

    @GetMapping("/album/{albumId}/addSong")
    public String addSong(Model model, HttpSession session, @PathVariable long albumId) {
        setHistory(session, "Add Song");

        model.addAttribute("title", "Add Song");
        model.addAttribute("headerList", new ArrayList<>(List.of(
                new DataItem("backButton", "/allSongs"))
        ));

        SongViewModel songViewModel = new SongViewModel();
        model.addAttribute("song", songViewModel);
        return "addSong";
    }

    @PostMapping("/album/{albumId}/addSong")
    public String addSong(@Valid @ModelAttribute SongViewModel viewModel, BindingResult errors, HttpSession session, @PathVariable long albumId) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "addSong";
        }
        Song song = new Song(
                albumId,
                viewModel.getUrl(),
                viewModel.getSongTitle(),
                viewModel.getTrackNumber(),
                viewModel.getDurationMS(),
                viewModel.isExplicit()
        );
        logger.debug(viewModel.toString());
        songService.saveSong(song);
        return "redirect:/allAlbums/fullAlbum/" + albumId;
    }


    @GetMapping("/fullSong/deleteSong/{id}")
    public String deleteSong(@PathVariable long id) {
        songService.deleteSong(id);
        return "redirect:/allSongs";
    }

    @GetMapping("/fullSong/printSong/{id}")
    public String printSong(@PathVariable long id) {
        songService.printSong(id);
        return "redirect:/allSongs";
    }

    private void setHistory(HttpSession session,String message){
        List<HistoryItem> history = (List<HistoryItem>) session.getAttribute("history");
        if (session.getAttribute("history") == null) {
            history = new ArrayList<>();
        }
        history.add(new HistoryItem(message, LocalDateTime.now()));
        session.setAttribute("history", history);
    }
}

