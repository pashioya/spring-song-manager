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
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView artists(HttpSession session) {
        setHistory(session, "All Artists");
        ModelAndView mav = new ModelAndView("allArtists");
        mav.addObject("title", "Artists");
        mav.addObject("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("allSongs"),
                new DataItem("allAlbums"),
                new DataItem("allArtists", "active")
        )));
        mav.addObject(("footerList"), new ArrayList<>(Arrays.asList(
                new DataItem("gitLab"),
                new DataItem("createArtist"),
                new DataItem("pageHistory")
        )));
        return mav;
    }

    @GetMapping("fullArtist/{id}")
    public ModelAndView fullArtist(@PathVariable long id, HttpSession session) {
        setHistory(session, "Full Artist: " + id);
        ModelAndView mav = new ModelAndView("fullArtist");
        try{
            mav.addObject("artist", artistService.getArtistById(id));
        }
        catch (Exception e){
            throw new EntityNotFoundException("Artist with id " + id + " not found",e);
        }
        mav.addObject("title", "Artists");
        mav.addObject("headerList", new ArrayList<>(Arrays.asList(
                new DataItem("allSongs"),
                new DataItem("allAlbums"),
                new DataItem("allArtists", "active")
        )));

        mav.addObject(("footerList"), new ArrayList<>(Arrays.asList(
                new DataItem("deleteArtist"),
                new DataItem("createAlbum", String.valueOf(id)),
                new DataItem("printArtist")
        )));
        mav.addObject("artist", artistService.getArtistById(id));
        mav.addObject("albums", artistService.getArtistById(id).getAlbums());
        return mav;
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
    public String addArtistWithAlbum(@Valid @ModelAttribute ArtistViewModel viewModel, BindingResult errors, @PathVariable long albumId) {
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

//    @PostMapping("/addArtist")
//    public String addArtist(@Valid @ModelAttribute ArtistViewModel viewModel, BindingResult errors) {
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(error -> logger.error(error.toString()));
//            return "addSong";
//        }
//        Artist artist = new Artist(
//                viewModel.getArtistName(),
//                viewModel.getArtistFollowers()
//        );
//
//        artistService.saveArtist(artist);
//        return "redirect:/allArtists";
//    }

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
