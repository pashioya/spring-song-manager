package com.kdg.springprojt5.controllers.mvc;

import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private final Logger logger;

    public IndexController() {
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
    }

    @GetMapping
    public ModelAndView index(HttpSession session) {
        setHistory(session, "Index");
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("title", "Song Manager");
        logger.info("Index page loading");
        return mav;
    }

    @GetMapping("/pageHistory")
    public ModelAndView pageHistory(HttpSession session) {
        setHistory(session, "Page History");
        ModelAndView mav = new ModelAndView("pageHistory");
        mav.addObject("title", "Page History");
        mav.addObject("pageHistory", session.getAttribute("history"));
        logger.info("Page History page loading");
        return mav;
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
