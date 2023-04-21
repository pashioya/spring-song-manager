package com.kdg.springprojt5.controllers.mvc;

import com.kdg.springprojt5.controllers.mvc.helper.DataItem;
import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import com.kdg.springprojt5.controllers.mvc.viewmodel.SongViewModel;
import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.service.SongService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/allSongs")
public class SongsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final SongService songService;

    @GetMapping
    public ModelAndView songs(HttpSession session) {
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
    public ModelAndView fullSong(@PathVariable Long id, HttpSession session) {
        setHistory(session, "Full Song: " + id);
        ModelAndView mav = new ModelAndView("fullSong");

        Song song = songService.getSongById(id);

        SongViewModel songViewModel = new SongViewModel(
                song.getId(),
                song.getUrl(),
                song.getSongTitle(),
                song.getTrackNumber(),
                song.getDurationMS(),
                song.isExplicit(),
                song.getUser().getUsername()
        );
        System.out.println(song.getUser());
        try {
            mav.addObject("song", songViewModel);
        } catch (Exception e) {
            throw new EntityNotFoundException("Song with id " + id + " not found");
        }
        logger.info("Song with id " + id + " found");

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
    public String addSong(Model model, HttpSession session, @PathVariable Long albumId) {
        setHistory(session, "Add Song");

        model.addAttribute("title", "Add Song");
        model.addAttribute("headerList", new ArrayList<>(List.of(
                new DataItem("backButton", "/allSongs"))
        ));
        logger.info("Add Song page loading");

        SongViewModel songViewModel = new SongViewModel();
        model.addAttribute("song", songViewModel);
        return "addSong";
    }

    @GetMapping("/fullSong/printSong/{id}")
    public String printSong(@PathVariable Long id) {
        songService.printSong(id);
        return "redirect:/allSongs";
    }

    private void setHistory(HttpSession session, String message) {
        List<HistoryItem> history = (List<HistoryItem>) session.getAttribute("history");
        if (session.getAttribute("history") == null) {
            history = new ArrayList<>();
        }
        history.add(new HistoryItem(message, LocalDateTime.now()));
        session.setAttribute("history", history);
    }
}

