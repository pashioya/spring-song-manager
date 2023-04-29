package com.kdg.springprojt5.controllers.mvc;


import com.kdg.springprojt5.controllers.mvc.helper.DataItem;
import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/allAlbums")
public class AlbumsController {
    final Logger logger;
    final AlbumService albumService;
    final SongService songService;
    final ArtistService artistService;

    public AlbumsController(AlbumService albumService, SongService songService, ArtistService artistService) {
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
        this.albumService = albumService;
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping()
    public ModelAndView albums(HttpSession session) {
        ModelAndView mav = new ModelAndView("allAlbums");
        mav.addObject("title", "Albums");
        mav.addObject("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("allSongs"),
                new DataItem("allAlbums", "active"),
                new DataItem("allArtists")
        )));

        mav.addObject(("footerList"), new ArrayList<>(Arrays.asList(
                new DataItem("gitLab"),
                new DataItem("pageHistory")
        )));
        setHistory(session, "All Albums");
        return mav;
    }

    @GetMapping("/fullAlbum/{id}")
    public ModelAndView fullAlbum(@PathVariable Long id, HttpSession session) {
        setHistory(session, "FullAlbum: " + id);
        ModelAndView mav = new ModelAndView("fullAlbum");
        mav.addObject("title", "fullAlbum");
        mav.addObject("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("allSongs"),
                new DataItem("allAlbums"),
                new DataItem("allArtists")

        )));
        mav.addObject("footerList", new ArrayList<>(Arrays.asList(
                new DataItem("deleteAlbum"),
                new DataItem("createSong", String.valueOf(id)),
                new DataItem("printAlbum")
        )));
        return mav;
    }

    @GetMapping("/fullAlbum/{id}/printAlbum")
    public String printAlbum(@PathVariable Long id) {
        albumService.printAlbum(id);
        return "redirect:/allAlbums";
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
