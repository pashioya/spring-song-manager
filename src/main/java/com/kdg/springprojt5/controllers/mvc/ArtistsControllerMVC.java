package com.kdg.springprojt5.controllers.mvc;

import com.kdg.springprojt5.controllers.mvc.helper.DataItem;
import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import com.kdg.springprojt5.controllers.mvc.viewmodel.ArtistViewModel;
import com.kdg.springprojt5.service.ArtistService;
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
@RequestMapping("/allArtists")
public class ArtistsControllerMVC {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final ArtistService artistService;
    private final ModelMapper modelMaper;

    @GetMapping()
    public ModelAndView artists(HttpSession session) {
        setHistory(session, "All Artists");
        ModelAndView mav = new ModelAndView("allArtists");
        mav.addObject("title", "Artists");
        mav.addObject(("footerList"), new ArrayList<>(Arrays.asList(
                new DataItem("gitLab"),
                new DataItem("createArtist"),
                new DataItem("pageHistory")
        )));

        mav.addObject("artists", artistService.getAllArtists()
                .stream()
                .map(artist -> modelMaper
                        .map(artist, ArtistViewModel.class))
                .toList());

        logger.info("artists() called");
        return mav;
    }

    @GetMapping("/fullArtist/{id}")
    public ModelAndView fullArtist(@PathVariable Long id, HttpSession session) {
        setHistory(session, "Full Artist: " + id);
        ModelAndView mav = new ModelAndView("fullArtist");
        mav.addObject("title", "Artists");


        mav.addObject(("footerList"), new ArrayList<>(Arrays.asList(
                new DataItem("deleteArtist"),
                new DataItem("createAlbum", String.valueOf(id)),
                new DataItem("printArtist")
        )));

        logger.info("full artist called");
        return mav;
    }

    @GetMapping("/fullArtist/printArtist/{id}")
    public String printArtist(@PathVariable Long id) {
        artistService.printArtist(id);
        return "redirect:/allArtists";
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
