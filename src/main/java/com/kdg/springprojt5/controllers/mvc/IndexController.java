package com.kdg.springprojt5.controllers.mvc;

import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String index(Model model,HttpSession session) {
        setHistory(session, "Index");
        model.addAttribute("title", "Song Manager");
        return "index";
    }

    @GetMapping("/pageHistory")
    public String pageHistory(Model model, HttpSession session) {
        setHistory(session, "Page History");
        model.addAttribute("title", "Page History");
        model.addAttribute("pageHistory", session.getAttribute("history"));
        return "pageHistory";
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
