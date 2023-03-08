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
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView songs(Model model, HttpSession session) {
        setHistory(session, "All Songs");
        ModelAndView mav = new ModelAndView("allSongs");
        mav.addObject("title", "Songs");
        mav.addObject("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("allSongs", "active"),
                new DataItem("allAlbums"),
                new DataItem("allArtists")

        )));
        mav.addObject("footerList", new ArrayList<>(Arrays.asList(
                new DataItem("gitLab"),
                new DataItem("pageHistory")
        )));
        return mav;
    }
    @GetMapping("/fullSong/{id}")
    public ModelAndView fullSong(Model model, @PathVariable long id, HttpSession session) {
        setHistory(session, "Full Song: " + id);
        ModelAndView mav = new ModelAndView("fullSong");
        try {
            mav.addObject("song", songService.getSongById(id));
            mav.addObject("artists", albumService.getAlbumById(songService.getSongById(id).getAlbumId()).getArtists());
        }
        catch (Exception e){
            throw new EntityNotFoundException("Song with id " + id + " not found");
        }

        mav.addObject("title", "Songs");
        mav.addObject("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("allSongs", "active"),
                new DataItem("allAlbums"),
                new DataItem("allArtists")
        )));
        mav.addObject("footerList", new ArrayList<>(Arrays.asList(
                new DataItem("deleteSong"),
                new DataItem("printSong")
        )));
        return mav;
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

