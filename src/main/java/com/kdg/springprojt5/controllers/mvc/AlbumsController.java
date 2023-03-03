package com.kdg.springprojt5.controllers.mvc;


import com.kdg.springprojt5.controllers.mvc.helper.DataItem;
import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import com.kdg.springprojt5.controllers.mvc.viewmodel.AlbumViewModel;
import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.StatusEnum;
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

import java.rmi.ServerError;
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
    public String albums(Model model, HttpSession session){
        model.addAttribute("title", "Albums");

        model.addAttribute("title", "Albums");
        model.addAttribute("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("homeButton"),
                new DataItem("allSongs"),
                new DataItem("allAlbums", "active"),
                new DataItem("allArtists")
        )));

        model.addAttribute(("footerList"), new ArrayList<>(Arrays.asList(
                new DataItem("gitLab"),
                new DataItem("pageHistory")
        )));
        if(session.getAttribute("alPageNumbers") == null){
            session.setAttribute("alPageNumbers", 1);
        }
        int alPageNumbers = (int) session.getAttribute("alPageNumbers");
        model.addAttribute("totalPages", albumService.getAllAlbums().size() / 20 + 1);
        model.addAttribute("alPageNumbers", alPageNumbers);
        model.addAttribute("albums", getAlbums(alPageNumbers, 20));
        setHistory(session, "All Albums");
        return "allAlbums";
    }

    public List<AlbumViewModel> getAlbums(int page, int pageSize) {
        List<Album> artists = albumService.getAllAlbums();
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, artists.size());

        return artists.subList(start, end).stream().map(a -> new AlbumViewModel(
                a.getId(),
                a.getAlbumName(),
                a.getOfficialTrackCount(),
                a.getAlbumStatus().toString(),
                a.getGenre(),
                a.getReleaseDate()
        )).toList();
    }


    @GetMapping("/nextPage")
    public String nextPage(HttpSession session){
        int alPageNumbers = (int) session.getAttribute("alPageNumbers");
        session.setAttribute("alPageNumbers", alPageNumbers + 1);
        return "redirect:/allAlbums";
    }

    @GetMapping("/previousPage")
    public String previousPage(HttpSession session){
        int alPageNumbers = (int) session.getAttribute("alPageNumbers");
        if(alPageNumbers > 1){
            session.setAttribute("alPageNumbers", alPageNumbers - 1);
        }
        return "redirect:/allAlbums";
    }

    @GetMapping("/firstPage")
    public String firstPage(HttpSession session){
        session.setAttribute("alPageNumbers", 1);
        return "redirect:/allAlbums";
    }

    @GetMapping("/lastPage")
    public String lastPage(HttpSession session){
        session.setAttribute("alPageNumbers", albumService.getAllAlbums().size() / 20 + 1);
        return "redirect:/allAlbums";
    }


    @GetMapping("/fullAlbum/{id}")
    public String fullAlbum(Model model, @PathVariable long id, HttpSession session) {
        setHistory(session, "FullAlbum: " + id);

        var album = albumService.getAlbumById(id);
        try {
            model.addAttribute("album", new AlbumViewModel(
                    album.getId(),
                    album.getAlbumName(),
                    album.getOfficialTrackCount(),
                    album.getAlbumStatus().toString(),
                    album.getGenre(),
                    album.getReleaseDate()
            ));
        } catch (Exception e) {
            throw new EntityNotFoundException("Album not found",e);
        }
        model.addAttribute("title", "fullAlbum");
        model.addAttribute("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("homeButton"),
                new DataItem("allSongs"),
                new DataItem("allAlbums"),
                new DataItem("allArtists")

        )));
        model.addAttribute("footerList", new ArrayList<>(Arrays.asList(
                new DataItem("deleteAlbum"),
                new DataItem("createSong", String.valueOf(id)),
                new DataItem("printAlbum")
        )));
        return "fullAlbum";
    }

    @GetMapping("/artist/{artistId}/addAlbum")
    public String addAlbum(Model model, HttpSession session, @PathVariable long artistId) {
        setHistory(session, "Add Album");

        model.addAttribute("title", "Add Album");
        model.addAttribute("headerList", new ArrayList<>(List.of(
                new DataItem("backButton", "/allAlbums"))
        ));
        AlbumViewModel albumViewModel = new AlbumViewModel();
        model.addAttribute("album", albumViewModel);
        return "addAlbum";
    }

    @PostMapping("/artist/{artistId}/addAlbum")
    public String addAlbum(@Valid @ModelAttribute AlbumViewModel viewModel,BindingResult errors, HttpSession session, @PathVariable long artistId) {
        Album album = new Album(
                viewModel.getAlbumName(),
                viewModel.getOfficialTrackCount(),
                StatusEnum.valueOf(viewModel.getAlbumStatus().toUpperCase()),
                viewModel.getGenre(),
                viewModel.getReleaseDate()
        );
        logger.info(albumService.saveAlbum(album, artistId).toString());

        return "redirect:/allAlbums";
    }

    @GetMapping("/fullAlbum/deleteAlbum/{id}")
    public String deleteAlbum(@PathVariable long id) throws ServerError {
        albumService.deleteAlbum(id);
        return "redirect:/allAlbums";
    }

    @GetMapping("/fullAlbum/printAlbum/{id}")
    public String printAlbum(@PathVariable long id) throws ServerError {
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
