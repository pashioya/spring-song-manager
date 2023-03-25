package com.kdg.springprojt5.controllers.mvc;


import com.kdg.springprojt5.controllers.mvc.helper.DataItem;
import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import com.kdg.springprojt5.controllers.mvc.viewmodel.AlbumViewModel;
import com.kdg.springprojt5.service.AlbumService;
import com.kdg.springprojt5.service.ArtistService;
import com.kdg.springprojt5.service.SongService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/allAlbums")
public class AlbumsController {
    Logger logger;
    AlbumService albumService;
    SongService songService;
    ArtistService artistService;

    public AlbumsController(AlbumService albumService, SongService songService, ArtistService artistService) {
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
        this.albumService = albumService;
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping()
    public ModelAndView albums(HttpSession session){
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
        var album = albumService.getAlbumById(id);
        try {
            mav.addObject("album", new AlbumViewModel(
                    album.getId(),
                    album.getAlbumName(),
                    album.getOfficialTrackCount(),
                    album.getAlbumStatus().toString(),
                    album.getGenre(),
                    album.getReleaseDate(),
                    album.getArtists(),
                    album.getSongs()
            ));
        } catch (Exception e) {
            throw new EntityNotFoundException("Album not found",e);
        }
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

    @GetMapping("/artist/{artistId}/addAlbum")
    public ModelAndView addAlbum(Model model, HttpSession session, @PathVariable Long artistId) {
        setHistory(session, "Add Album");
        ModelAndView mav = new ModelAndView("addAlbum");
        mav.addObject("title", "Add Album");
        AlbumViewModel albumViewModel = new AlbumViewModel();
        mav.addObject("album", albumViewModel);
        return mav;
    }

    @GetMapping("/fullAlbum/{id}/deleteAlbum")
    public ModelAndView deleteAlbum(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("allAlbums");
        mav.addObject("title", "Delete Album");
        mav.addObject("album", albumService.getAlbumById(id));
        return mav;
    }

    @GetMapping("/fullAlbum/{id}/printAlbum")
    public String printAlbum(@PathVariable Long id) {
        albumService.printAlbum(id);
        return "redirect:/allAlbums";
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
