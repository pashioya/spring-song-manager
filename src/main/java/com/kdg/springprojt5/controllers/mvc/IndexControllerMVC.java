package com.kdg.springprojt5.controllers.mvc;

import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import com.kdg.springprojt5.security.AdminOnly;
import com.kdg.springprojt5.security.CustomUserDetails;
import com.kdg.springprojt5.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexControllerMVC {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final UserService userService;

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

    @AdminOnly
    @GetMapping("/adminPage")
    public ModelAndView adminPage(HttpSession session, Authentication authentication) {
        setHistory(session, "Admin Page");

        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
        Long userId = currentUser.getUserId();

        ModelAndView mav = new ModelAndView("adminPage");
        mav.addObject("title", "Admin Page");
        mav.addObject("users", userService.getAllUsers());
        mav.addObject("currentUser", userService.getUserById(userId));
        logger.info("Admin Page loading");
        return mav;
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
