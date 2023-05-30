package com.kdg.springprojt5.controllers.mvc;

import com.kdg.springprojt5.controllers.mvc.helper.DataItem;
import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import com.kdg.springprojt5.controllers.mvc.viewmodel.SongViewModel;
import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.security.AdminOnly;
import com.kdg.springprojt5.security.CustomUserDetails;
import com.kdg.springprojt5.service.SongService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Controller
@AllArgsConstructor
@RequestMapping("/allSongs")
public class SongsControllerMVC {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final SongService songService;
    private final ModelMapper modelMapper;
    private final ExecutorService executorService;

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


    @PostMapping(path = "/upload", consumes = {"multipart/form-data"})
    @AdminOnly
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file,
                                   @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (file.isEmpty()) {
            return new ModelAndView("addSongs");
        }

        // Start a new thread to handle file processing
        executorService.execute(() -> processCsvFile(file, userDetails.getUserId()));

        return new ModelAndView("redirect:/allSongs");
    }

    @Async
    public void processCsvFile(MultipartFile file, long userId) {
        try (InputStream inputStream = file.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the CSV file
                if (!StringUtils.isEmpty(line)) {
                    // Simulate processing time
                    Thread.sleep(1000);
                    // Parse the line and create a RecordDto object
                    songService.saveSong(parseCsvLine(line, userId));
                }
            }

            // Notify completion or update status if required
            logger.info("File processing complete");

        } catch (IOException | InterruptedException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }

    private Song parseCsvLine(String line, long userId) {
        // Split the line by comma or any other delimiter as per your CSV format
        String[] fields = line.split(",");

        // Create a new Song object and set the fields accordingly
        return new Song(
                Long.valueOf(fields[0]),
                fields[5],
                fields[3],
                Integer.parseInt(fields[4]),
                Double.parseDouble(fields[1]),
                Boolean.parseBoolean(fields[2]),
                userId
        );
    }
}

