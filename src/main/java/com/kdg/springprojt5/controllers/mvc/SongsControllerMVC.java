package com.kdg.springprojt5.controllers.mvc;

import com.kdg.springprojt5.controllers.mvc.helper.DataItem;
import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import com.kdg.springprojt5.controllers.mvc.viewmodel.SongViewModel;
import com.kdg.springprojt5.service.SongService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
public class SongsControllerMVC {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final SongService songService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ModelAndView songs(HttpSession session) {
        setHistory(session, "All Songs");
        ModelAndView mav = new ModelAndView("allSongs");
        mav.addObject("title", "Songs");
        mav.addObject("footerList", new ArrayList<>(Arrays.asList(
                new DataItem("gitLab"),
                new DataItem("pageHistory")
        )));

        mav.addObject("songs", songService.getAllSongs()
                .stream()
                .map(song -> modelMapper
                        .map(song, SongViewModel.class))
                .toList());

        return mav;
    }

    @GetMapping("/fullSong/{id}")
    public ModelAndView fullSong(@PathVariable Long id, HttpSession session) {
        setHistory(session, "Full Song: " + id);
        ModelAndView mav = new ModelAndView("fullSong");
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

