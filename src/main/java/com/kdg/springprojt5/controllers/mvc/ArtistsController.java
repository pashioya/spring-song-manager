package com.kdg.springprojt5.controllers.mvc;

import com.kdg.springprojt5.controllers.mvc.helper.DataItem;
import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import com.kdg.springprojt5.controllers.mvc.viewmodel.ArtistViewModel;
import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.service.ArtistService;
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
@RequestMapping("/allArtists")
public class ArtistsController {

    private final Logger logger;
    private final ArtistService artistService;

    public ArtistsController(ArtistService  artistService) {
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
        this.artistService = artistService;
    }

    @GetMapping()
    public String artists(Model model, HttpSession session) {
        setHistory(session, "All Artists");
        model.addAttribute("title", "Artists");
        model.addAttribute("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("homeButton"),
                new DataItem("allSongs"),
                new DataItem("allAlbums"),
                new DataItem("allArtists", "active")
        )));
        model.addAttribute(("footerList"), new ArrayList<>(Arrays.asList(
                new DataItem("gitLab"),
                new DataItem("createArtist"),
                new DataItem("pageHistory")
        )));


        if(session.getAttribute("arPageNumbers") == null){
            session.setAttribute("arPageNumbers", 1);
        }
        int arPageNumbers = (int) session.getAttribute("arPageNumbers");

        model.addAttribute("artists", getArtists(arPageNumbers, 20));
        model.addAttribute("totalPages", artistService.getAllArtists().size() / 20 + 1);
        model.addAttribute("arPageNumbers", arPageNumbers);
        return "allArtists";
    }

    @GetMapping("/nextPage")
    public String nextPage(HttpSession session){
        int arPageNumbers = (int) session.getAttribute("arPageNumbers");
        session.setAttribute("arPageNumbers", arPageNumbers + 1);
        return "redirect:/allArtists";
    }

    @GetMapping("/previousPage")
    public String previousPage(HttpSession session){
        int arPageNumbers = (int) session.getAttribute("arPageNumbers");
        if(arPageNumbers > 1){
            session.setAttribute("arPageNumbers", arPageNumbers - 1);
        }
        return "redirect:/allArtists";
    }

    @GetMapping("/firstPage")
    public String firstPage(HttpSession session){
        session.setAttribute("arPageNumbers", 1);
        return "redirect:/allArtists";
    }

    @GetMapping("/lastPage")
    public String lastPage(HttpSession session){
        session.setAttribute("arPageNumbers", artistService.getAllArtists().size() / 20 + 1);
        return "redirect:/allArtists";
    }



//    two parameters to your method to represent the current page and the number of artists per page:
//    calculate which artists to return based on the page and pageSize parameters
    public List<Artist> getArtists(int page, int pageSize) {
        List<Artist> artists = artistService.getAllArtists();
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, artists.size());
        return artists.subList(start, end);
    }

    @GetMapping("fullArtist/{id}")
    public String fullArtist(Model model, @PathVariable long id, HttpSession session) {
        setHistory(session, "Full Artist: " + id);
        try{
            model.addAttribute("artist", artistService.getArtistById(id));
        }
        catch (Exception e){
            throw new EntityNotFoundException("Artist with id " + id + " not found",e);
        }
        model.addAttribute("title", "Artists");
        model.addAttribute("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("homeButton"),
                new DataItem("allSongs"),
                new DataItem("allAlbums"),
                new DataItem("allArtists", "active")

        )));

        model.addAttribute(("footerList"), new ArrayList<>(Arrays.asList(
                new DataItem("deleteArtist"),
                new DataItem("createAlbum", String.valueOf(id)),
                new DataItem("printArtist")
        )));
        model.addAttribute("artist", artistService.getArtistById(id));
        model.addAttribute("albums", artistService.getArtistById(id).getAlbums());
        return "fullArtist";
    }


    @GetMapping("/addArtist")
    public String addArtist(Model model,HttpSession session) {
        setHistory(session, "Add Artist");
        model.addAttribute("title", "Add Artist");
        model.addAttribute("headerList", new ArrayList<>(List.of(
                new DataItem("backButton", "/allArtists"))
        ));

        ArtistViewModel artistViewModel = new ArtistViewModel();
        model.addAttribute("artist", artistViewModel);
        return "addArtist";
    }

    @GetMapping("fullAlbum/{albumId}/addArtist")
    public String addArtistToAlbum(Model model,HttpSession session, @PathVariable long albumId) {
        setHistory(session, "Add Artist");
        model.addAttribute("title", "Add Artist");
        model.addAttribute("headerList", new ArrayList<>(List.of(
                new DataItem("backButton", "/allArtists"))
        ));

        ArtistViewModel artistViewModel = new ArtistViewModel();
        model.addAttribute("artist", artistViewModel);
        return "addArtist";
    }
    @PostMapping("fullAlbum/{albumId}/addArtist")
    public String addArtistWithAlbum(@Valid @ModelAttribute ArtistViewModel viewModel, Model model, BindingResult errors, @PathVariable long albumId) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "addSong";
        }
        Artist artist = new Artist(
                viewModel.getArtistName(),
                viewModel.getArtistFollowers()
        );
        artistService.saveArtist(artist);
        artistService.addArtistToAlbum(artist, albumId);
        return "redirect:/allAlbums/fullAlbum/" + albumId;
    }

    @PostMapping("/addArtist")
    public String addArtist(@Valid @ModelAttribute ArtistViewModel viewModel, Model model, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "addSong";
        }
        Artist artist = new Artist(
                viewModel.getArtistName(),
                viewModel.getArtistFollowers()
        );

        artistService.saveArtist(artist);
        return "redirect:/allArtists";
    }

    @GetMapping("/fullArtist/deleteArtist/{id}")
    public String deleteArtist(@PathVariable long id) {
        artistService.deleteArtist(id);
        return "redirect:/allArtists";
    }

    @GetMapping("/fullArtist/printArtist/{id}")
    public String printArtist(@PathVariable long id) {
        artistService.printArtist(id);
        return "redirect:/allArtists";
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
